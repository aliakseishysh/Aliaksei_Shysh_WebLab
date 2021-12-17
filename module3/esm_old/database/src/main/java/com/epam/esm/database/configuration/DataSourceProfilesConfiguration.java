package com.epam.esm.database.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = "com.epam.esm.database.configuration")
@PropertySource("classpath:/properties/database.properties")
@EnableTransactionManagement
public class DataSourceProfilesConfiguration {
}
