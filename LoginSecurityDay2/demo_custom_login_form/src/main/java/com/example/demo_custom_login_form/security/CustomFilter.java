package com.example.demo_custom_login_form.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class CustomFilter extends OncePerRequestFilter {
    @Autowired
    private CustomUserDetailService customUserDetailService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //lay ra email trong session
        String email = (String)request.getSession().getAttribute("MY_SESSION");
        if (email == null){
            filterChain.doFilter(request,response);
            return;
        }
        UserDetails userDetails = customUserDetailService.loadUserByUsername(email);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                userDetails.getUsername(),
                null,
                userDetails.getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(token);
        filterChain.doFilter(request,response);

    }
}
