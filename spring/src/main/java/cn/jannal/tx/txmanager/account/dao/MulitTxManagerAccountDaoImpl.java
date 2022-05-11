package cn.jannal.tx.txmanager.account.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public class MulitTxManagerAccountDaoImpl implements MulitTxManagerAccountDao {

    @Override
    public int out(String outAccount, BigDecimal money, JdbcTemplate jdbcTemplate) {
        String sql = "update account set money= money - ? where name= ?";
        return jdbcTemplate.update(sql, money, outAccount);
    }

    @Override
    public int in(String inAccount, BigDecimal money, JdbcTemplate jdbcTemplate) {
        String sql = "update account set money=money + ? where name = ?";
        return jdbcTemplate.update(sql, money, inAccount);
    }


}
