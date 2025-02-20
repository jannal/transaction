package org.jannal.tx.txmanager.account.service;


import java.math.BigDecimal;

public interface MulitTxManagerAccountService {

    public void transferNoException0(final String outAccount, final String inAccount, final BigDecimal money);

    public void transferNoException1(final String outAccount, final String inAccount, final BigDecimal money);
}
