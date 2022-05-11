package cn.jannal.tx.declarative.annotation.aspectj;


import cn.jannal.tx.declarative.annotation.account.service.AccountService;
import cn.jannal.tx.declarative.annotation.aspectj.configuration.AspectJDataSourceConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;

/**
 * 运行此类需要添加
 * -javaagent:/Users/jannal/aspectj1.9/lib/aspectjweaver.jar
 */
public class AspectJMain {
    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AspectJDataSourceConfiguration.class);
        AccountService accountService = (AccountService) context.getBean("accountService");
        //transferNoException(accountService);
        //transferThrowRuntimeExcetion(accountService);
        //transferThrowCheckException(accountService);
        transferNoTransactionalInvokeTransactionalException(accountService);

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
