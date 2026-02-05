package com.petfoster.config;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.petfoster.security.JwtAuthenticationFilter;
import com.petfoster.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
        .csrf(csrf -> csrf.disable())
            .sessionManagement(sm ->
                    sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
            	    .requestMatchers("/auth/**").permitAll()
            	    .requestMatchers("/error").permitAll()
            	    .anyRequest().authenticated()
            	)

            .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin())); // allow H2 console

        http.addFilterBefore(jwtFilter,
                UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    
    
}
