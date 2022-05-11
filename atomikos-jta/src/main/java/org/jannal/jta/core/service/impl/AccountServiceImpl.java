package org.jannal.jta.core.service.impl;


import org.jannal.jta.core.ds0.mapper.Account0Mapper;
import org.jannal.jta.core.ds1.mapper.Account1Mapper;
import org.jannal.jta.core.entity.Account;
import org.jannal.jta.core.entity.AccountTransfer;
import org.jannal.jta.core.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;


@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private Account0Mapper account0Mapper;
    @Autowired
    private Account1Mapper account1Mapper;

    @Override
    @Transactional
    public void transfer(AccountTransfer accountTransfer) {
        String accountFromId = accountTransfer.getAccountFromId();
        String accountToId = accountTransfer.getAccountToId();
        BigDecimal amount = accountTransfer.getAmount();
        //转账账户
        Account accountFromExist = account0Mapper.findByAccountId(accountFromId);
        if (accountFromExist == null) {
            throw new RuntimeException(accountFromId + "不存在");
        }
        accountFromExist = account0Mapper.findByAmountIdForUpdate(accountFromId);
        if (accountFromExist.getAmount().subtract(amount).compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException(accountFromId + "账户余额不足");
        }
        Account accountNew = new Account();
        accountNew.setId(accountFromExist.getId());
        accountNew.setAmount(accountFromExist.getAmount().subtract(amount));
        account0Mapper.update(accountNew);

        //接收账户
        Account accountToExist = account1Mapper.findByAccountId(accountToId);
        if (accountToExist == null) {
            throw new RuntimeException(accountToId + "不存在");
        }
        Account accountNew1 = new Account();
        accountNew1.setId(accountToExist.getId());
        accountNew1.setAmount(accountToExist.getAmount().add(amount));
        account1Mapper.update(accountNew1);

        //模拟异常
        if (accountTransfer.isMockException()) {
            throw new RuntimeException("模拟出现异常");
        }
    }
}
