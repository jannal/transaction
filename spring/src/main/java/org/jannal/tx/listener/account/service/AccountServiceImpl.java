package org.jannal.tx.listener.account.service;


import org.jannal.tx.listener.account.dao.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service("accountService")
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    @Autowired
    ApplicationEventPublisher eventPublisher;

    public void transfer0(final String outAccount, final String inAccount, final BigDecimal money) {
        accountDao.out(outAccount, money);
        accountDao.in(inAccount, money);
        eventPublisher.publishEvent(new AccountEvent(this));
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
}
