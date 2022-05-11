package org.jannal.account0;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
//@EnableDubbo
@MapperScan(basePackages = "org.jannal.account0.core.**.dao.mapper")
public class Account0ProviderApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(Account0ProviderApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
    }

}
