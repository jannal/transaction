package cn.jannal.tx.txmanager.account.service;


import cn.jannal.tx.txmanager.account.dao.MulitTxManagerAccountDao;
import cn.jannal.tx.txmanager.annotation.Transactional_0;
import cn.jannal.tx.txmanager.annotation.Transactional_1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Service("mulitTxManagerAccountService")
public class MulitTxManagerAccountServiceImpl implements MulitTxManagerAccountService {

    @Autowired
    private MulitTxManagerAccountDao accountDao;
    @Resource(name = "jdbcTemplate0")
    private JdbcTemplate jdbcTemplate0;
    @Resource(name = "jdbcTemplate1")
    private JdbcTemplate jdbcTemplate1;


    private void transfer0(final String outAccount, final String inAccount, final BigDecimal money, JdbcTemplate jdbcTemplate) {
        accountDao.out(outAccount, money, jdbcTemplate);
        accountDao.in(inAccount, money, jdbcTemplate);
    }

    @Override
    @Transactional_0
    public void transferNoException0(String outAccount, String inAccount, BigDecimal money) {
        transfer0(outAccount, inAccount, money, jdbcTemplate0);
    }

    @Override
    @Transactional_1
    public void transferNoException1(String outAccount, String inAccount, BigDecimal money) {
        transfer0(outAccount, inAccount, money, jdbcTemplate1);
    }
}
