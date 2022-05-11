package org.jannal.account;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
//@EnableDubbo
@MapperScan(basePackages = "org.jannal.account.core.**.dao.mapper")
public class AccountProviderApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(AccountProviderApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
    }

}
