package cn.jannal.tx.declarative.txadvice.account.service;


import cn.jannal.tx.declarative.txadvice.account.dao.TxAccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service("txAccountService")
public class TxAccountServiceImpl implements TxAccountService {

    @Autowired
    private TxAccountDao TxAccountDao;

    public void transfer0(final String outAccount, final String inAccount, final BigDecimal money) {
        TxAccountDao.out(outAccount, money);
        TxAccountDao.in(inAccount, money);
    }

    @Override
    public void transferNoTransactionalInvokeTransactionalException(String outAccount, String inAccount, BigDecimal money) {
        transferThrowRuntimeExcetion(outAccount, inAccount, money);
    }

    @Override
    public void transferThrowCheckException(final String outAccount, final String inAccount, final BigDecimal money) throws Exception {
        transfer0(outAccount, inAccount, money);
        throw new Exception("出现异常");

    }

    @Override
    public void transferThrowRuntimeExcetion(String outAccount, String inAccount, BigDecimal money) {
        transfer0(outAccount, inAccount, money);
        int d = 1 / 0;
    }

    @Override
    public void transferNoException(String outAccount, String inAccount, BigDecimal money) {
        transfer0(outAccount, inAccount, money);
    }
}
