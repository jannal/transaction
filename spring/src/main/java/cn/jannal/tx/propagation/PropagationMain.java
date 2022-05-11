package cn.jannal.tx.propagation;


import cn.jannal.tx.propagation.account.service.PropagationAccountService;
import cn.jannal.tx.propagation.configuration.PropagationDataSourceConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;

public class PropagationMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(PropagationDataSourceConfiguration.class);
        PropagationAccountService accountService = (PropagationAccountService) context.getBean("propagationAccountService");
        transferRequired(accountService);
        //transferRequiredSavePoint(accountService);
        //transferRequired2(accountService);
        context.stop();
    }

    public static void transferRequired(PropagationAccountService accountService) {
        accountService.transferRequired("jannal", BigDecimal.valueOf(1000));
    }

    public static void transferRequiredSavePoint(PropagationAccountService accountService) {
        accountService.transferRequiredSavePoint("jannal", BigDecimal.valueOf(1000));
    }

    public static void transferRequired2(PropagationAccountService accountService) {
        accountService.transferRequired2("jannal", BigDecimal.valueOf(1000));
    }


}
