package com.lexora.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Database configuration for Lexora.
 * Connection properties are loaded from application-dev.yml using environment variables:
 * DB_HOST, DB_PORT, DB_NAME, DB_USERNAME, DB_PASSWORD
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.lexora.repository")
public class DatabaseConfig {
    // DataSource bean is auto-configured by Spring Boot from application.yml
    // Override here if custom configuration is needed in future phases
}
