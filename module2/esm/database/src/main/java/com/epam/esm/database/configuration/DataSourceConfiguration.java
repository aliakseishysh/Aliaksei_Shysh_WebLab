package com.epam.esm.database.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = "com.epam.esm")
@EnableTransactionManagement
@PropertySource("classpath:/properties/database.properties")
public class DataSourceConfiguration {
    private Environment environment;

    @Autowired
    public DataSourceConfiguration(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public JdbcTemplate createJdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    @Profile("production")
    public DataSource setup() {
        HikariConfig configuration = new HikariConfig();
        configuration.setDriverClassName(environment.getRequiredProperty("database.driver_class_name"));
        //configuration.setDataSourceClassName(environment.getRequiredProperty("database.data_source_class_name"));
        configuration.setUsername(environment.getRequiredProperty("database.user"));
        configuration.setPassword(environment.getRequiredProperty("database.password"));
        configuration.setJdbcUrl(environment.getRequiredProperty("database.jdbc_url"));
        return new HikariDataSource(configuration);
    }

    @Bean
    public PlatformTransactionManager getTransactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}
