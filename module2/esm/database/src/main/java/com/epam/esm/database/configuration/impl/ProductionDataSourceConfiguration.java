package com.epam.esm.database.configuration.impl;

import com.epam.esm.database.configuration.DataSourceConfiguration;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
@Profile("production")
public class ProductionDataSourceConfiguration implements DataSourceConfiguration {
    private Environment environment;

    @Autowired
    public ProductionDataSourceConfiguration(Environment environment) {
        this.environment = environment;
    }

    @Bean
    @Profile("production")
    public DataSource setup() {
        HikariConfig configuration = new HikariConfig();
        configuration.setDriverClassName(environment.getRequiredProperty("database.driver_class_name"));
        configuration.setUsername(environment.getRequiredProperty("database.user"));
        configuration.setPassword(environment.getRequiredProperty("database.password"));
        configuration.setJdbcUrl(environment.getRequiredProperty("database.jdbc_url"));
        return new HikariDataSource(configuration);
    }
}
