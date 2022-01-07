package com.epam.esm.database.configuration.impl;

import com.epam.esm.database.configuration.DataSourceConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Profile("production")
@Configuration
@PropertySource("classpath:/application.properties")
public class ProductionDataSourceConfiguration implements DataSourceConfiguration {
}
