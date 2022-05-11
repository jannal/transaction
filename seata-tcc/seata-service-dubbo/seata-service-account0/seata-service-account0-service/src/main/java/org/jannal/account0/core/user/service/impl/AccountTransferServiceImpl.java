package org.jannal.account0.core.user.service.impl;


import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.jannal.account0.core.user.dao.mapper.AccountMapper;
import org.jannal.account0.core.user.dao.mapper.TransferSerialMapper;
import org.jannal.account0.core.user.entity.Account;
import org.jannal.account0.core.user.entity.TransferSerial;
import org.jannal.account0.core.user.service.AccountTransferService;
import org.jannal.common.exception.ValidateParamsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

@Service
@Slf4j
public class AccountTransferServiceImpl implements AccountTransferService {
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private TransferSerialMapper transferSerialMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void tryPayment(TransferSerial transferSerial) {
        final String transactionId = RootContext.getXID();
        String accountFromId = transferSerial.getAccountFromId();
        String transferSerialNumber = transferSerial.getTransferSerialNumber();
        BigDecimal amount = transferSerial.getAmount();
        log.info("事务ID:[{}],tryPayment -> transferSerialNumber:[{}], accountFromId:[{}] , accountToId:[{}]"
                , transactionId
                , transferSerialNumber
                , accountFromId
                , transferSerial.getAccountToId()
        );
        TransferSerial transferSerialExist = transferSerialMapper.findBySerialNumberForUpdate(transferSerialNumber);
        if (transferSerialExist != null) {
            log.warn("事务ID:[{}],{}已经处理，忽略重复提交", transactionId, transferSerialNumber);
            return;
        }
        Account accountExist = accountMapper.findByAmountIdForUpdate(accountFromId);
        if (accountExist == null) {
            throw ValidateParamsException.valueOfParamsIllegal(accountFromId + "账户不存在");
        }
        //判断账户余额是否充足
        if (accountExist.getAmount().subtract(amount).compareTo(BigDecimal.ZERO) < 0) {
            throw ValidateParamsException.valueOfParamsIllegal(accountFromId + "账户余额不足");
        }
        Account accountNew = new Account();
        accountNew.setId(accountExist.getId());
        accountNew.setAmount(accountExist.getAmount().subtract(amount));
        accountNew.setFreezedAmount(accountExist.getFreezedAmount().add(amount));
        accountNew.setUpdateTime(new Date());
        accountMapper.update(accountNew);

        Date date = new Date();
        transferSerial.setCreateTime(date);
        transferSerial.setUpdateTime(date);
        transferSerial.setStatus(TransferSerial.Status.PENDING.getStatus());
        transferSerialMapper.insert(transferSerial);

    }

    @Override
    public void confirmPayment(String transferSerialNumber) {
        final String transactionId = RootContext.getXID();
        TransferSerial transferSerialExist = transferSerialMapper.findBySerialNumberForUpdate(transferSerialNumber);
        // 发生空提交，这是不允许的，需要终止
        if (transferSerialExist == null) {
            //log.warn("事务ID:[{}],{}不存在，忽略提交", transactionId, transferSerialNumber);
            throw ValidateParamsException.valueOfParamsIllegal(transferSerialNumber + "事务状态不存在");
        }
        if (TransferSerial.Status.DISCARD.getStatus() == transferSerialExist.getStatus()) {
            log.warn("事务ID:[{}],{}是回滚状态，已经回滚的事务不能再次提交", transactionId, transferSerialNumber);
            throw ValidateParamsException.valueOfParamsIllegal(transferSerialNumber + "已经回滚的事务不能再次提交");
        }
        if (TransferSerial.Status.FINISHED.getStatus() == transferSerialExist.getStatus()) {
            log.warn("事务ID:[{}],{}已经是完成状态，忽略重复提交", transactionId, transferSerialNumber);
            return;
        }
        //更新流水状态
        TransferSerial transferSerialNew = new TransferSerial();
        transferSerialNew.setId(transferSerialExist.getId());
        transferSerialNew.setStatus(TransferSerial.Status.FINISHED.getStatus());
        transferSerialMapper.update(transferSerialNew);

        //这里使用悲观锁（由于try已经预留资源，这里无需再次判断金额是否超出余额）
        Account account = accountMapper.findByAmountIdForUpdate(transferSerialExist.getAccountFromId());
        //冻结金额释放
        Account accountNew = new Account();
        accountNew.setId(account.getId());
        accountNew.setFreezedAmount(account.getFreezedAmount().subtract(transferSerialExist.getAmount()));
        accountNew.setUpdateTime(new Date());
        accountMapper.update(accountNew);


    }

    @Override
    public void cancelPayment(TransferSerial transferSerial) {
        final String transactionId = RootContext.getXID();
        final String transferSerialNumber = transferSerial.getTransferSerialNumber();
        //非悲观锁，避免锁表
        TransferSerial transferSerialExist = transferSerialMapper.findBySerialNumber(transferSerialNumber);
        Date date = new Date();
        // 如果插入失败（Try正在插入），就等待重试。插入成功，可确保后续Try不会执行
        if (transferSerialExist == null) {
            log.warn("事务ID:[{}],{}不存在，插入取消状态", transactionId, transferSerialNumber);
            // 插入一条空记录，确保后续的try不会被执行
            transferSerial.setCreateTime(date);
            transferSerial.setUpdateTime(date);
            transferSerial.setStatus(TransferSerial.Status.DISCARD.getStatus());
            transferSerialMapper.insert(transferSerial);
            return;
        }
        //存在后再使用悲观锁，防止悬挂
        transferSerialExist = transferSerialMapper.findBySerialNumberForUpdate(transferSerialNumber);
        if (TransferSerial.Status.DISCARD.getStatus() == transferSerialExist.getStatus()) {
            log.warn("事务ID:[{}],{}是回滚状态，忽略重复提交", transactionId, transferSerialNumber);
            return;
        }

        if (TransferSerial.Status.FINISHED.getStatus() == transferSerialExist.getStatus()) {
            log.warn("事务ID:[{}],{}是完成状态，已经提交的事务不能进行回滚", transactionId, transferSerialNumber);
            throw ValidateParamsException.valueOfParamsIllegal(transferSerialNumber + "已经提交的事务不能进行回滚");
        }

        Account account = accountMapper.findByAmountIdForUpdate(transferSerialExist.getAccountFromId());
        if (account.getFreezedAmount().subtract(transferSerialExist.getAmount())
                .compareTo(BigDecimal.ZERO) < 0) {
            // 除非数据库数据被篡改，一般不可能出现这种情况，这里做一个防御
            String message = String.format("事务ID:[%s],冻结金额[%s]-转账金额[%s]<0,检查数据是否错误",
                    transactionId,
                    account.getFreezedAmount(),
                    transferSerialExist.getAmount());
            throw ValidateParamsException.valueOfParamsIllegal(message);
        }
        //更新流水状态
        TransferSerial transferSerialNew = new TransferSerial();
        transferSerialNew.setId(transferSerialExist.getId());
        transferSerialNew.setStatus(TransferSerial.Status.DISCARD.getStatus());
        transferSerialMapper.update(transferSerialNew);

        //冻结金额释放
        Account accountNew = new Account();
        accountNew.setId(account.getId());
        accountNew.setAmount(account.getAmount().add(transferSerialExist.getAmount()));
        accountNew.setFreezedAmount(account.getFreezedAmount().subtract(transferSerialExist.getAmount()));
        accountNew.setUpdateTime(new Date());
        accountMapper.update(accountNew);
    }
}
