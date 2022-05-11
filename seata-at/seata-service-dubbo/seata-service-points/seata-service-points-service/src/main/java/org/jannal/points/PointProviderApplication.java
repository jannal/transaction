package org.jannal.points;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
//@EnableDubbo
@MapperScan(basePackages = "org.jannal.points.core.**.dao.mapper")
public class PointProviderApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(PointProviderApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
    }

}
