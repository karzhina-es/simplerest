package com.example.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@Import({
        DatabaseConfig.class,
        SecurityConfig.class
})
@ComponentScan(basePackages = "com.example")
public class ApplicationConfig {
}
