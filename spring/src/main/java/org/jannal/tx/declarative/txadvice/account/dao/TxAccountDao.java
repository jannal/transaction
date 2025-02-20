package org.jannal.tx.declarative.txadvice.account.dao;


import java.math.BigDecimal;

public interface TxAccountDao {
    public int out(String outAccount, BigDecimal money);

    public int in(String inAccount, BigDecimal money);
}
