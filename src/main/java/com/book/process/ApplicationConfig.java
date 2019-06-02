package com.book.process;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

@Configuration
@EnableTransactionManagement
@ComponentScan
class ApplicationConfig {

    @Value("${spring.datasource.driver-class-name}")
    private String driver;

    @Value("${SPRING_DATASOURCE_URL}")
    private String url;

    @Value("${SPRING_DATASOURCE_USERNAME}")
    private String user;

    @Value("${SPRING_DATASOURCE_PASSWORD}")
    private String pass;

    @Value("${SPRING_DATASOURCE_C3P0_MIN_POOL_SIZE}")
    private int minPoolSize;

    @Value("${SPRING_DATASOURCE_C3P0_MAX_POOL_SIZE}")
    private int maxPoolSize;

    @Value("${SPRING_DATASOURCE_C3P0_AQUIRE_INCREMENT}")
    private int aquireIncrement;

    @Bean
    public DataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        cpds.setDriverClass(driver);
        cpds.setJdbcUrl(url);
        cpds.setUser(user);
        cpds.setPassword(pass);
        cpds.setMinPoolSize(minPoolSize);
        cpds.setAcquireIncrement(aquireIncrement);
        cpds.setMaxPoolSize(maxPoolSize);

        return cpds;
    }

    @Bean
    public PlatformTransactionManager transactionManager() throws PropertyVetoException{
        DataSourceTransactionManager txManager = new DataSourceTransactionManager();
        txManager.setDataSource(dataSource());
        return txManager;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

}