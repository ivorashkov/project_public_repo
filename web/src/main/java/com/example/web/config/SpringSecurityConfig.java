package com.example.web.config;


import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SpringSecurityConfig {

  @Bean
  @Order(1)
  public SecurityFilterChain adminFilterChain(HttpSecurity http) throws Exception {
    http
        .securityMatcher("/admin/**")
        .authorizeHttpRequests(authorize -> authorize
            .anyRequest().hasRole("admin")
        )
        .httpBasic();

    return http.build();
  }

  @Bean
  @Order(2)
  public SecurityFilterChain userFilterChain(HttpSecurity http) throws Exception {
    http
        .securityMatcher("/user/**")
        .authorizeHttpRequests(authorize -> authorize
            .anyRequest().hasRole("user")
        )
        .httpBasic();

    return http.build();
  }

  @Bean
  @Order(3)
  public SecurityFilterChain allAccessFilterChain(HttpSecurity http) throws Exception {
    http
        .securityMatcher("/**")
        .securityMatcher("/auth/**")
        .authorizeHttpRequests()
        .anyRequest()
        .authenticated()
        .and()
        .httpBasic();

    return http.build();
  }

}
