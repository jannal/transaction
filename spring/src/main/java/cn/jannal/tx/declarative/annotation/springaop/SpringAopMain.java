package cn.jannal.tx.declarative.annotation.springaop;


import cn.jannal.tx.declarative.annotation.account.service.AccountService;
import cn.jannal.tx.declarative.annotation.springaop.configuration.SpringAopDataSourceConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;

public class SpringAopMain {
    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringAopDataSourceConfiguration.class);
        AccountService accountService = (AccountService) context.getBean("accountService");
        //transferNoException(accountService);
        //transferThrowRuntimeExcetion(accountService);
        //transferThrowCheckException(accountService);
        //transferNoTransactionalInvokeTransactionalException(accountService);
        transferManualRollback(accountService);

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

    public static void transferManualRollback(AccountService accountService) {
        accountService.transferManualRollback("jannal", "tom", BigDecimal.valueOf(1000));
    }
}
