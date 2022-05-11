package cn.jannal.tx.programmatic.account.service;


import java.math.BigDecimal;

public interface AccountService {
    public void transfer(final String outAccount, final String inAccount, final BigDecimal money, final boolean mockException);
}
