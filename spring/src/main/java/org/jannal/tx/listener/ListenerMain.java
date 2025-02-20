package org.jannal.tx.listener;


import org.jannal.tx.listener.account.service.AccountService;
import org.jannal.tx.listener.configuration.ListenerDataSourceConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;

public class ListenerMain {
    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ListenerDataSourceConfiguration.class);
        AccountService accountService = (AccountService) context.getBean("accountService");
        transferNoException(accountService);
        //transferThrowRuntimeExcetion(accountService);
        //transferThrowCheckException(accountService);
        ///transferNoTransactionalInvokeTransactionalException(accountService);

    }

    public static void transferThrowCheckException(AccountService accountService) throws Exception {
        accountService.transferThrowCheckException("jannal", "tom", BigDecimal.valueOf(1000));
    }

    public static void transferThrowRuntimeExcetion(AccountService accountService) {
        accountService.transferThrowRuntimeExcetion("jannal", "tom", BigDecimal.valueOf(1000));
    }

    public static void transferNoException(AccountService accountService) {
        accountService.transferNoException("jannal", "tom", BigDecimal.valueOf(1000));
    }

    public static void transferNoTransactionalInvokeTransactionalException(AccountService accountService) {
        accountService.transferNoTransactionalInvokeTransactionalException("jannal", "tom", BigDecimal.valueOf(1000));
    }
}
