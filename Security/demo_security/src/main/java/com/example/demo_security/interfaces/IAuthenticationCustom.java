package com.example.demo_security.interfaces;

import org.springframework.security.core.Authentication;

public interface IAuthenticationCustom {
    Authentication getAuthentication();
}
