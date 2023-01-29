package com.auto.net.config;

import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.auto.net"})
@ConfigurationPropertiesScan(basePackages = {"com.auto.net.config"})
public class AppConfig {
}
