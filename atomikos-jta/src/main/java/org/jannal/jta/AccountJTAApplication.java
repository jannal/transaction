package org.jannal.jta;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class AccountJTAApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(AccountJTAApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
    }

}
