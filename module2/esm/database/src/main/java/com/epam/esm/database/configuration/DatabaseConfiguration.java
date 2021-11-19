package com.epam.esm.database.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:/properties/database.properties")
public class DatabaseConfiguration implements EnvironmentAware {
    private Environment environment;

    @Override
    public void setEnvironment(final Environment environment) {
        this.environment = environment;
    }

    @Bean
    @Profile("development")
    public DataSource createDevelopmentDataSource() {
        return new HikariDataSource();
    }

    @Bean
    @Profile("production")
    public DataSource createProductionDataSource() {
        HikariConfig configuration = new HikariConfig();
        configuration.setUsername(environment.getRequiredProperty("database.user"));
        configuration.setPassword(environment.getRequiredProperty("database.password"));
        configuration.setJdbcUrl(environment.getRequiredProperty("database.jdbc_url"));
        return new HikariDataSource(configuration);
    }

    @Bean
    public JdbcTemplate createJdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public PlatformTransactionManager createTransactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}
