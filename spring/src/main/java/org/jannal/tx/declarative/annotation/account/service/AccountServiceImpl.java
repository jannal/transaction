package org.jannal.tx.declarative.annotation.account.service;


import org.jannal.tx.declarative.annotation.account.dao.AccountDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.math.BigDecimal;

@Service("accountService")
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    public void transfer0(final String outAccount, final String inAccount, final BigDecimal money) {
        accountDao.out(outAccount, money);
        accountDao.in(inAccount, money);
    }

    @Override
    public void transferNoTransactionalInvokeTransactionalException(String outAccount, String inAccount, BigDecimal money) {
        transferThrowRuntimeExcetion(outAccount, inAccount, money);
    }

    @Override
    @Transactional
    public void transferThrowCheckException(final String outAccount, final String inAccount, final BigDecimal money) throws Exception {
        transfer0(outAccount, inAccount, money);
        throw new Exception("出现异常");

    }

    @Override
    @Transactional
    public void transferThrowRuntimeExcetion(String outAccount, String inAccount, BigDecimal money) {
        transfer0(outAccount, inAccount, money);
        int d = 1 / 0;
    }

    @Override
    @Transactional
    public void transferNoException(String outAccount, String inAccount, BigDecimal money) {
        transfer0(outAccount, inAccount, money);
    }

    @Override
    @Transactional
    public void transferManualRollback(String outAccount, String inAccount, BigDecimal money) {
        try {
            transferThrowRuntimeExcetion(outAccount, inAccount, money);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            //手动执行回滚操作
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
    }
}
