package org.jannal.tx.txmanager.account.dao;


import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;

public interface MulitTxManagerAccountDao {
    public int out(String outAccount, BigDecimal money, JdbcTemplate jdbcTemplate);

    public int in(String inAccount, BigDecimal money, JdbcTemplate jdbcTemplate);
}
