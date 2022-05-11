package org.jannal.jta;

import org.jannal.jta.core.entity.AccountTransfer;
import org.jannal.jta.core.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AccountJTATest {
    @Autowired
    private AccountService accountService;

    /**
     * 正常
     */
    @Test
    public void testTransfer() {
        AccountTransfer accountTransfer = new AccountTransfer();
        accountTransfer.setAccountFromId("jannal");
        accountTransfer.setAccountToId("tom");
        accountTransfer.setAmount(new BigDecimal(1000));
        accountService.transfer(accountTransfer);
    }

    /**
     * 第二个数据源业务异常
     */
    @Test
    public void testTransferException() {
        AccountTransfer accountTransfer = new AccountTransfer();
        accountTransfer.setAccountFromId("jannal");
        //不存在的账户
        accountTransfer.setAccountToId("jack");
        accountTransfer.setAmount(new BigDecimal(1000));
        accountService.transfer(accountTransfer);
    }

    /**
     * 第一个和第二个数据源业务都正常，方法结束前异常（模拟）
     */
    @Test
    public void testTransferMockException() {
        AccountTransfer accountTransfer = new AccountTransfer();
        accountTransfer.setAccountFromId("jannal");
        accountTransfer.setAccountToId("tom");
        accountTransfer.setAmount(new BigDecimal(1000));
        accountTransfer.setMockException(true);
        accountService.transfer(accountTransfer);
    }
}
