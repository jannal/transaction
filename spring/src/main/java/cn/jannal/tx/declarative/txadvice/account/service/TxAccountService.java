package cn.jannal.tx.declarative.txadvice.account.service;


import java.math.BigDecimal;

public interface TxAccountService {

    public void transferNoTransactionalInvokeTransactionalException(final String outAccount, final String inAccount, final BigDecimal money);

    public void transferThrowCheckException(final String outAccount, final String inAccount, final BigDecimal money) throws Exception;

    public void transferThrowRuntimeExcetion(final String outAccount, final String inAccount, final BigDecimal money);

    public void transferNoException(final String outAccount, final String inAccount, final BigDecimal money);
}
