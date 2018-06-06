package com.example.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationUtilsImpl implements AuthenticationUtils {

    @Override
    public String getCurrentAuthenticationName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
