package org.example.config;


import com.mysql.cj.jdbc.Driver;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
@EnableTransactionManagement
@Configuration
@ComponentScan(basePackages = "org.example")
@PropertySource("classpath:db.properties")
public class AppConfig {
    @Value("${url}")
    private String url;

    @Value("${name}")
    private String userName;

    @Value("${password}")
    private String password;


    private String driver = "com.mysql.cj.jdbc.Driver";

    @Bean()
    public DataSource getDataSource() {
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setJdbcUrl(url);
        hikariDataSource.setUsername(userName);
        hikariDataSource.setPassword(password);
        hikariDataSource.setDriverClassName(driver);
        return hikariDataSource;
    }

    @Bean()
    public PlatformTransactionManager getTxManager() {
        return new DataSourceTransactionManager(getDataSource());
    }


}
