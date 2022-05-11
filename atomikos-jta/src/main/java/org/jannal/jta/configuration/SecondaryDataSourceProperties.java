package org.jannal.jta.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.datasource.secondary")
@Data
public class SecondaryDataSourceProperties {
    private String url;
    private String username;
    private String password;
    private int minPoolSize = 2;
    private int maxPoolSize = 10;
    /** max-lifetime 连接最大存活时间 s**/
    private int maxLifetime = 60;
    /** borrow-connection-timeout 获取连接失败重新获等待最大时间s，在这个时间内如果有可用连接，将返回 **/
    private int borrowConnectionTimeout = 20;
    /** login-timeout java数据库连接池，最大可等待获取datasouce的时间s **/
    private int loginTimeout = 30;
    /** maintenance-interval 连接回收时间s **/
    private int maintenanceInterval = 600;
    /** max-idle-time 最大闲置时间s，超过最小连接池连接的连接将将关闭 **/
    private int maxIdleTime = 600;
    /** test-query 测试SQL **/
    private String testQuery = "SELECT 1";

}
