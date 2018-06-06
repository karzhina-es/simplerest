package com.example.filter;

import com.example.dto.CustomerDto;
import com.example.service.api.CustomerService;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;

@Component
public class AuthorizationFilter extends GenericFilterBean {

    private final String TOKEN_PREFIX = "Bearer ";

    private final CustomerService customerService;

    public AuthorizationFilter(CustomerService customerService) {
        this.customerService = Objects.requireNonNull(customerService);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String header = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);

        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            throw new BadCredentialsException("Header invalid");
        }

        SecurityContextHolder.getContext().setAuthentication(getAuthentication(header));

        filterChain.doFilter(servletRequest, servletResponse);
    }


    private UsernamePasswordAuthenticationToken getAuthentication(String header) {
        Optional<CustomerDto> customer = customerService.getCustomerById(header.replace(TOKEN_PREFIX, ""));
        if (customer.isPresent()) {
            return new UsernamePasswordAuthenticationToken(customer.get().getId(), null, Collections.emptyList());
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }
}
