package org.jannal.tx.declarative.txadvice;


import org.jannal.tx.declarative.txadvice.account.service.TxAccountService;
import org.jannal.tx.declarative.txadvice.configuration.TxDataSourceConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;

public class TxMain {
    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TxDataSourceConfiguration.class);
        TxAccountService accountService = (TxAccountService) context.getBean("txAccountService");
        //transferNoException(accountService);
        //transferThrowRuntimeExcetion(accountService);
        //transferThrowCheckException(accountService);
        transferNoTransactionalInvokeTransactionalException(accountService);

    }

    public static void transferThrowCheckException(TxAccountService accountService) throws Exception {
        accountService.transferThrowCheckException("jannal", "tom", BigDecimal.valueOf(1000));
    }

    public static void transferThrowRuntimeExcetion(TxAccountService accountService) {
        accountService.transferThrowRuntimeExcetion("jannal", "tom", BigDecimal.valueOf(1000));
    }

    public static void transferNoException(TxAccountService accountService) {
        accountService.transferNoException("jannal", "tom", BigDecimal.valueOf(1000));
    }

    public static void transferNoTransactionalInvokeTransactionalException(TxAccountService accountService) {
        accountService.transferNoTransactionalInvokeTransactionalException("jannal", "tom", BigDecimal.valueOf(1000));
    }
}
