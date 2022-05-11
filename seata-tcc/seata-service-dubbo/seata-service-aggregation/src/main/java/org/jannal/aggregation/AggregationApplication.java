package org.jannal.aggregation;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class AggregationApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(AggregationApplication.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }
}
