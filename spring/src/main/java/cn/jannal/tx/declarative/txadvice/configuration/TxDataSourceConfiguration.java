package cn.jannal.tx.declarative.txadvice.configuration;


import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@PropertySource(value = {"classpath:jdbc.properties"})
@ComponentScan({"cn.jannal.tx.declarative.txadvice.account"})
@ImportResource(locations = {"classpath:spring-tx.xml"})
public class TxDataSourceConfiguration {
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
