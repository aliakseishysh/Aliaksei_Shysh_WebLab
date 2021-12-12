package com.epam.esm.database.configuration.impl;

import com.epam.esm.database.configuration.DataSourceConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
@Profile("development")
public class DevelopmentDataSourceConfiguration implements DataSourceConfiguration {
    private final Environment environment;

    @Autowired
    public DevelopmentDataSourceConfiguration(Environment environment) {
        this.environment = environment;
    }

    @Bean
    @Profile("development")
    public DataSource setup() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(environment.getRequiredProperty("database.driver_class_name"));
        driverManagerDataSource.setUsername(environment.getRequiredProperty("database.user"));
        driverManagerDataSource.setPassword(environment.getRequiredProperty("database.password"));
        driverManagerDataSource.setUrl(environment.getRequiredProperty("database.jdbc_url"));
        return driverManagerDataSource;
    }
}
