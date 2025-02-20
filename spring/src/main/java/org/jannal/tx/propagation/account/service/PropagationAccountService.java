package org.jannal.tx.propagation.account.service;


import java.math.BigDecimal;

public interface PropagationAccountService {

    public void transferRequired(final String account, final BigDecimal money);

    public void transferRequiredSavePoint(final String account, final BigDecimal money);

    public void transferRequired2(final String account, final BigDecimal money);

    public void transferRequiredThrowRuntimeException(String account, BigDecimal money);

    public void transferRequiredThrowRuntimeExceptionNew(String account, BigDecimal money);

}
