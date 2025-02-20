package org.jannal.tx.declarative.annotation.account.dao;


import java.math.BigDecimal;

public interface AccountDao {
    public int out(String outAccount, BigDecimal money);

    public int in(String inAccount, BigDecimal money);
}
