package cn.jannal.tx.propagation.account.service;


import cn.jannal.tx.propagation.account.dao.PropagationAccountDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.math.BigDecimal;

@Service("propagationAccountService")
@Slf4j
public class PropagationAccountServiceImpl implements PropagationAccountService {

    @Autowired
    private PropagationAccountDao accountDao;

    @Autowired
    private PropagationAccountService propagationAccountService;


    /**
     * 抛出异常
     * UnexpectedRollbackException: Transaction rolled back because it has been marked as rollback-only
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void transferRequired(String account, BigDecimal money) {
        accountDao.in(account, money);
        try {
            propagationAccountService.transferRequiredThrowRuntimeException(account, money);
        } catch (Exception e) {
            //虽然捕获了异常，但是因为没有开启新事务，而当前事务因为异常已经被标记为rollback了
            log.error(e.getMessage(), e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void transferRequiredSavePoint(String account, BigDecimal money) {
        accountDao.in(account, money);
        //只回滚以下异常
        Object savePoint = TransactionAspectSupport.currentTransactionStatus().createSavepoint();
        try {
            propagationAccountService.transferRequiredThrowRuntimeException("tom", money);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().rollbackToSavepoint(savePoint);
            log.error(e.getMessage(), e);
        }
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void transferRequiredThrowRuntimeException(String account, BigDecimal money) {
        accountDao.in(account, money);
        int d = 1 / 0;
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void transferRequired2(String account, BigDecimal money) {
        accountDao.in(account, money);
        try {
            propagationAccountService.transferRequiredThrowRuntimeExceptionNew(account, money);
        } catch (Exception e) {
            //虽然捕获了异常，但是因为没有开启新事务，而当前事务因为异常已经被标记为rollback了
            log.error(e.getMessage(), e);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void transferRequiredThrowRuntimeExceptionNew(String account, BigDecimal money) {
        accountDao.in(account, money);
        int d = 1 / 0;
    }
}
