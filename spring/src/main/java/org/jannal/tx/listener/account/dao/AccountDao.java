package org.jannal.tx.listener.account.dao;


import java.math.BigDecimal;

public interface AccountDao {
    public int out(String outAccount, BigDecimal money);

    public int in(String inAccount, BigDecimal money);
}
