package org.jannal.tx.programmatic.configuration;


import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;

@Configuration
@PropertySource(value = {"classpath:jdbc.properties"})
@ComponentScan({"org.jannal.tx.programmatic.account"})
public class DataSourceConfiguration {
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

    /**
     * 配置事务管理器模板
     */
    @Bean
    public TransactionTemplate transactionTemplate(DataSourceTransactionManager transactionManager) {
        TransactionTemplate transactionTemplate = new TransactionTemplate();
        transactionTemplate.setTransactionManager(transactionManager);
        return transactionTemplate;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource datasource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(datasource);
        return jdbcTemplate;
    }
}
