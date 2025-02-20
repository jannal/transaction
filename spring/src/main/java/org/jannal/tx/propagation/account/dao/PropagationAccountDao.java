package org.jannal.tx.propagation.account.dao;


import java.math.BigDecimal;

public interface PropagationAccountDao {
    public int out(String outAccount, BigDecimal money);

    public int in(String inAccount, BigDecimal money);
}
