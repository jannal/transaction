package org.jannal.tx.declarative.txadvice.account.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public class TxAccountDaoImpl implements TxAccountDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int out(String outAccount, BigDecimal money) {
        String sql = "update account set money= money - ? where name= ?";
        return this.jdbcTemplate.update(sql, money, outAccount);
    }

    @Override
    public int in(String inAccount, BigDecimal money) {
        String sql = "update account set money=money + ? where name = ?";
        return this.jdbcTemplate.update(sql, money, inAccount);
    }


}
