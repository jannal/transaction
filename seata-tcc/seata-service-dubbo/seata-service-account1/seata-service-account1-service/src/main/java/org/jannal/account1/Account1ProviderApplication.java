package org.jannal.account1;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
//@EnableDubbo
@MapperScan(basePackages = "org.jannal.account1.core.**.dao.mapper")
public class Account1ProviderApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(Account1ProviderApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
    }

}
