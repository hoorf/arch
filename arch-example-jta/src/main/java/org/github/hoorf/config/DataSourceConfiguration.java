package org.github.hoorf.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfiguration {

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.jta.atomikos.datasource.primary")
    public DataSource primaryDatasource() {
        return new AtomikosDataSourceBean();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.jta.atomikos.datasource.second")
    public DataSource secondDatasource() {
        return new AtomikosDataSourceBean();
    }


    @Bean
    public JdbcTemplate primaryJdbcTemplate() {
        return new JdbcTemplate(primaryDatasource());
    }

    @Bean
    public JdbcTemplate secondJdbcTemplate() {
        return new JdbcTemplate(secondDatasource());
    }


}
