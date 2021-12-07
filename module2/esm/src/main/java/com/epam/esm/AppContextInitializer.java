package com.epam.esm;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

public class AppContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(ConfigurableApplicationContext ac) {
        ConfigurableEnvironment appEnvironment = ac.getEnvironment();
        appEnvironment.addActiveProfile("production");
    }
}

