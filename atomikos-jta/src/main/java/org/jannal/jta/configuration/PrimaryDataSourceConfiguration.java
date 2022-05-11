package org.jannal.jta.configuration;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@EnableConfigurationProperties(PrimaryDataSourceProperties.class)
@MapperScan(basePackages = {"org.jannal.jta.core.ds0.mapper"}, sqlSessionFactoryRef = "sqlSessionFactory")
public class PrimaryDataSourceConfiguration {
    @Bean(name = "dataSource")
    @Primary
    public DataSource dataSource(PrimaryDataSourceProperties primaryDataSourceProperties) throws SQLException {
        MysqlXADataSource mysqlXADataSource = new MysqlXADataSource();
        mysqlXADataSource.setUrl(primaryDataSourceProperties.getUrl());
        mysqlXADataSource.setPinGlobalTxToPhysicalConnection(true);
        mysqlXADataSource.setPassword(primaryDataSourceProperties.getPassword());
        mysqlXADataSource.setUser(primaryDataSourceProperties.getUsername());

        AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
        atomikosDataSourceBean.setUniqueResourceName("dataSource");
        atomikosDataSourceBean.setXaDataSource(mysqlXADataSource);
        atomikosDataSourceBean.setMinPoolSize(primaryDataSourceProperties.getMinPoolSize());
        atomikosDataSourceBean.setMaxPoolSize(primaryDataSourceProperties.getMaxPoolSize());
        atomikosDataSourceBean.setMaxLifetime(primaryDataSourceProperties.getMaxLifetime());
        atomikosDataSourceBean.setBorrowConnectionTimeout(primaryDataSourceProperties.getBorrowConnectionTimeout());
        atomikosDataSourceBean.setLoginTimeout(primaryDataSourceProperties.getLoginTimeout());
        atomikosDataSourceBean.setMaintenanceInterval(primaryDataSourceProperties.getMaintenanceInterval());
        atomikosDataSourceBean.setMaxIdleTime(primaryDataSourceProperties.getMaxIdleTime());
        atomikosDataSourceBean.setTestQuery(primaryDataSourceProperties.getTestQuery());
        return atomikosDataSourceBean;

    }

    @Bean(name = "sqlSessionFactory")
    @Primary
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setLazyLoadingEnabled(true);
        //configuration.setLogImpl(StdOutImpl.class);
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setDefaultExecutorType(ExecutorType.REUSE);
        configuration.setCacheEnabled(false);
        configuration.setDefaultStatementTimeout(5000);
        bean.setConfiguration(configuration);
        return bean.getObject();
    }

    @Bean(name = "sqlSessionTemplate")
    @Primary
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
