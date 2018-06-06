package com.example.filter;

import com.example.domain.Customer;
import com.example.service.api.CustomerService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
import java.util.ArrayList;
import java.util.Optional;

@Component
public class AuthorizationFilter extends GenericFilterBean {

    private final String TOKEN_PREFIX = "Bearer ";
    private final String HEADER_NAME = "Authorization";

    private final CustomerService customerService;

    public AuthorizationFilter(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String header = httpServletRequest.getHeader(HEADER_NAME);

        Authentication auth = getAuthentication(header);
        if (header == null || !header.startsWith(TOKEN_PREFIX) || auth == null) {
            throw new BadCredentialsException("Header invalid");
        }

        SecurityContextHolder.getContext().setAuthentication(auth);

        filterChain.doFilter(servletRequest, servletResponse);
    }


    private UsernamePasswordAuthenticationToken getAuthentication(String header) {
        Optional<Customer> customer = customerService.getCustomerById(header.replace(TOKEN_PREFIX, ""));
        if (customer.isPresent()) {
            return new UsernamePasswordAuthenticationToken(customer.get().getId(), null, new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }
}
