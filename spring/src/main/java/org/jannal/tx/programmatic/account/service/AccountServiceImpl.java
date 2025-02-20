package org.jannal.tx.programmatic.account.service;


import org.jannal.tx.programmatic.account.dao.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.math.BigDecimal;

@Service("accountService")
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;
    @Autowired
    private TransactionTemplate transactionTemplate;

    @Override
    public void transfer(final String outAccount, final String inAccount, final BigDecimal money, final boolean mockException) {
        //TransactionCallbackWithoutResult是没有返回值的
        //TransactionCallback是有返回值的
        transactionTemplate.executeWithoutResult(transactionStatus -> {
            accountDao.out(outAccount, money);
            if (mockException) {
                int d = 1 / 0;
            }
            accountDao.in(inAccount, money);
        });
    }
}
