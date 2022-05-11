package cn.jannal.tx.declarative.annotation.account.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public class AccountDaoImpl implements AccountDao {

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
