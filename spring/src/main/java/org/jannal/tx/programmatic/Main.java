package org.jannal.tx.programmatic;


import org.jannal.tx.programmatic.account.service.AccountService;
import org.jannal.tx.programmatic.configuration.DataSourceConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DataSourceConfiguration.class);
        AccountService accountService = (AccountService) context.getBean("accountService");
        //transferHasException(accountService);
        transferNoException(accountService);

        AccountService accountService0 = (AccountService) context.getBean("accountService0");
        //transferHasException(accountService0);
        transferNoException(accountService0);
    }

    public static void transferHasException(AccountService accountService) {
        accountService.transfer("jannal", "tom", BigDecimal.valueOf(1000), true);
    }

    public static void transferNoException(AccountService accountService) {
        accountService.transfer("jannal", "tom", BigDecimal.valueOf(1000), false);
    }

}
