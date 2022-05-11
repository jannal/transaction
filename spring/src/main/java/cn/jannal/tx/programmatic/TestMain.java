package cn.jannal.tx.programmatic;


import cn.jannal.tx.programmatic.account.service.AccountService;
import cn.jannal.tx.programmatic.configuration.DataSourceConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DataSourceConfiguration.class)
public class TestMain {

    //@Resource(name = "accountService")
    //private AccountService accountService;
    @Resource(name = "accountService0")
    private AccountService accountService;

    @Test
    public void testTransferNoException() {
        accountService.transfer("jannal", "tom", BigDecimal.valueOf(1000), false);
    }

    @Test(expected = ArithmeticException.class)
    public void testTransferHasException() {
        accountService.transfer("jannal", "tom", BigDecimal.valueOf(1000), true);
    }
}
