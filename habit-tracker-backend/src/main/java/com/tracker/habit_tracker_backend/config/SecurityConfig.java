package com.tracker.habit_tracker_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.config.annotation.web.configuration.;


@Configuration
@EnableWebSecurity
public class SecurityConfig  {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http
                .authorizeHttpRequests(auth ->{
                    auth.requestMatchers("/api/test").permitAll();
                    auth.anyRequest().authenticated();
                })
                .formLogin(Customizer.withDefaults())
                .build();
    }
//    @Bean
//    public org.springframework.web.cors.CorsConfigurationSource corsConfigurationSource() {
//        org.springframework.web.cors.CorsConfiguration config = new org.springframework.web.cors.CorsConfiguration();
//        config.addAllowedOrigin("http://localhost:3000"); // Allow the React app
//        config.addAllowedMethod("*"); // Allow all HTTP methods
//        config.addAllowedHeader("*"); // Allow all headers
//        org.springframework.web.cors.UrlBasedCorsConfigurationSource source = new org.springframework.web.cors.UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", config);
//        return source;
//    }

}
