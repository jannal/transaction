package org.jannal.tx.listener.configuration;


import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@PropertySource(value = {"classpath:jdbc.properties"})
@ComponentScan({"org.jannal.tx.listener.account"})
@EnableTransactionManagement
public class ListenerDataSourceConfiguration {
    @Value("${jdbc.username}")
    private String jdbcUsername;
    @Value("${jdbc.password}")
    private String jdbcPassword;
    @Value("${jdbc.driver}")
    private String jdbcDriverClass;
    @Value("${jdbc.url}")
    private String jdbcUrl;


    @Bean
    public DataSource datasource() {
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setJdbcUrl(jdbcUrl);
        hikariDataSource.setUsername(jdbcUsername);
        hikariDataSource.setDriverClassName(jdbcDriverClass);
        hikariDataSource.setPassword(jdbcPassword);
        return hikariDataSource;
    }

    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager(dataSource);
        return dataSourceTransactionManager;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource datasource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(datasource);
        return jdbcTemplate;
    }
}
