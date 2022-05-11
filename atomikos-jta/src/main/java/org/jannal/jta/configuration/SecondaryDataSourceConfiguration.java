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
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@EnableConfigurationProperties(SecondaryDataSourceProperties.class)
@MapperScan(basePackages = {"org.jannal.jta.core.ds1.mapper"}, sqlSessionFactoryRef = "sqlSessionFactory2")
public class SecondaryDataSourceConfiguration {
    @Bean(name = "dataSource2")
    public DataSource dataSource(SecondaryDataSourceProperties secondaryDataSourceProperties) throws SQLException {
        MysqlXADataSource mysqlXADataSource = new MysqlXADataSource();
        mysqlXADataSource.setUrl(secondaryDataSourceProperties.getUrl());
        mysqlXADataSource.setPinGlobalTxToPhysicalConnection(true);
        mysqlXADataSource.setPassword(secondaryDataSourceProperties.getPassword());
        mysqlXADataSource.setUser(secondaryDataSourceProperties.getUsername());

        AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
        atomikosDataSourceBean.setUniqueResourceName("dataSource2");
        atomikosDataSourceBean.setXaDataSource(mysqlXADataSource);
        atomikosDataSourceBean.setMinPoolSize(secondaryDataSourceProperties.getMinPoolSize());
        atomikosDataSourceBean.setMaxPoolSize(secondaryDataSourceProperties.getMaxPoolSize());
        atomikosDataSourceBean.setMaxLifetime(secondaryDataSourceProperties.getMaxLifetime());
        atomikosDataSourceBean.setBorrowConnectionTimeout(secondaryDataSourceProperties.getBorrowConnectionTimeout());
        atomikosDataSourceBean.setLoginTimeout(secondaryDataSourceProperties.getLoginTimeout());
        atomikosDataSourceBean.setMaintenanceInterval(secondaryDataSourceProperties.getMaintenanceInterval());
        atomikosDataSourceBean.setMaxIdleTime(secondaryDataSourceProperties.getMaxIdleTime());
        atomikosDataSourceBean.setTestQuery(secondaryDataSourceProperties.getTestQuery());
        return atomikosDataSourceBean;
    }

    @Bean(name = "sqlSessionFactory2")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource2") DataSource dataSource) throws Exception {
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

    @Bean(name = "sqlSessionTemplate2")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactory2") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
