package org.jannal.tx.txmanager.configuration;


import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
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
@ComponentScan({"org.jannal.tx.txmanager.account"})
@EnableTransactionManagement
public class MulitManagerDataSourceConfiguration {

    @Bean(name = "datasource0")
    public HikariDataSource datasource0(
            @Value("${jdbc0.username}") String jdbcUsername,
            @Value("${jdbc0.password}") String jdbcPassword,
            @Value("${jdbc0.driver}") String jdbcDriverClass,
            @Value("${jdbc0.url}") String jdbcUrl) {
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setPoolName("datasource0");
        hikariDataSource.setJdbcUrl(jdbcUrl);
        hikariDataSource.setUsername(jdbcUsername);
        hikariDataSource.setDriverClassName(jdbcDriverClass);
        hikariDataSource.setPassword(jdbcPassword);
        return hikariDataSource;
    }

    @Bean(name = "datasource1")
    public HikariDataSource datasource1(
            @Value("${jdbc1.username}") String jdbcUsername,
            @Value("${jdbc1.password}") String jdbcPassword,
            @Value("${jdbc1.driver}") String jdbcDriverClass,
            @Value("${jdbc1.url}") String jdbcUrl) {
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setPoolName("datasource1");
        hikariDataSource.setJdbcUrl(jdbcUrl);
        hikariDataSource.setUsername(jdbcUsername);
        hikariDataSource.setDriverClassName(jdbcDriverClass);
        hikariDataSource.setPassword(jdbcPassword);
        return hikariDataSource;
    }


    @Bean(name = "transactionManager0")
    public DataSourceTransactionManager transactionManager0(@Qualifier(value = "datasource0") DataSource dataSource0) {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager(dataSource0);
        return dataSourceTransactionManager;
    }

    @Bean(name = "transactionManager1")
    public DataSourceTransactionManager transactionManager1(@Qualifier(value = "datasource1") DataSource dataSource1) {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager(dataSource1);
        return dataSourceTransactionManager;
    }

    @Bean(name = "jdbcTemplate0")
    public JdbcTemplate jdbcTemplate0(@Qualifier(value = "datasource0") DataSource datasource0) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(datasource0);
        return jdbcTemplate;
    }

    @Bean(name = "jdbcTemplate1")
    public JdbcTemplate jdbcTemplate1(@Qualifier(value = "datasource1") DataSource datasource1) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(datasource1);
        return jdbcTemplate;
    }
}
