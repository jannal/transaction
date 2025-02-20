package org.jannal.tx.txmanager;


import org.jannal.tx.txmanager.account.service.MulitTxManagerAccountService;
import org.jannal.tx.txmanager.configuration.MulitManagerDataSourceConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;

public class MulitMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MulitManagerDataSourceConfiguration.class);
        MulitTxManagerAccountService accountService = (MulitTxManagerAccountService) context.getBean("mulitTxManagerAccountService");
        transferNoException0(accountService);
        transferNoException1(accountService);
    }

    public static void transferNoException0(MulitTxManagerAccountService accountService) {
        accountService.transferNoException0("jannal", "tom", BigDecimal.valueOf(1000));
    }

    public static void transferNoException1(MulitTxManagerAccountService accountService) {
        accountService.transferNoException1("jannal", "tom", BigDecimal.valueOf(1000));
    }

}
