package cn.jannal.tx.programmatic.account.service;

import cn.jannal.tx.programmatic.account.dao.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.math.BigDecimal;

@Service("accountService0")
public class AccountService0Impl implements AccountService {
    @Autowired
    private AccountDao accountDao;
    @Autowired
    private PlatformTransactionManager txManager;

    @Override
    public void transfer(String outAccount, String inAccount, BigDecimal money, boolean mockException) {
        //定义事务
        DefaultTransactionDefinition defaultTransactionDefinition = new DefaultTransactionDefinition();
        defaultTransactionDefinition.setPropagationBehaviorName("PROPAGATION_REQUIRED");
        //启动事务
        TransactionStatus txStatus = txManager.getTransaction(defaultTransactionDefinition);
        try {
            accountDao.out(outAccount, money);
            if (mockException) {
                int d = 1 / 0;
            }
            accountDao.in(inAccount, money);
            txManager.commit(txStatus);
        } catch (Throwable e) {
            txManager.rollback(txStatus);
            throw e;
        }
    }
}
