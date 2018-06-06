package com.example.config;

import com.example.filter.AuthorizationFilter;
import com.example.service.api.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomerService customerService;

    @Autowired
    public SecurityConfig(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/customers/**").access("isAuthenticated()")
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new AuthorizationFilter(customerService), AnonymousAuthenticationFilter.class);
    }

}
