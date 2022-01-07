package com.epam.esm.database.configuration.impl;

import com.epam.esm.database.configuration.DataSourceConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Profile("development")
@Configuration
@PropertySource("classpath:/application.properties")
public class DevelopmentDataSourceConfiguration implements DataSourceConfiguration {
    private final Environment environment;

    @Autowired
    public DevelopmentDataSourceConfiguration(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public DataSource setup() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(environment.getRequiredProperty("spring.datasource.driver-class-name"));
        driverManagerDataSource.setUsername(environment.getRequiredProperty("spring.datasource.username"));
        driverManagerDataSource.setPassword(environment.getRequiredProperty("spring.datasource.password"));
        driverManagerDataSource.setUrl(environment.getRequiredProperty("spring.datasource.url"));
        return driverManagerDataSource;
    }
}
