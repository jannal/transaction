package org.jannal.tx.declarative.annotation.account.service;


import java.math.BigDecimal;

public interface AccountService {
    /**
     * 在内部调用，没有@Transactional注解的方法调用有@Transactional注解的方法，不会回滚
     */
    public void transferNoTransactionalInvokeTransactionalException(final String outAccount, final String inAccount, final BigDecimal money);

    public void transferThrowCheckException(final String outAccount, final String inAccount, final BigDecimal money) throws Exception;

    public void transferThrowRuntimeExcetion(final String outAccount, final String inAccount, final BigDecimal money);

    public void transferNoException(final String outAccount, final String inAccount, final BigDecimal money);

    public void transferManualRollback(final String outAccount, final String inAccount, final BigDecimal money);
}
