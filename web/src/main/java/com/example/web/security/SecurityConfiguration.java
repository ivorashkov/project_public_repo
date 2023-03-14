package com.example.web.security;

import com.example.web.model.enums.RoleType;
import com.example.web.security.jwt.JwtAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

  private final JwtAuthFilter jwtAuthFilter;
  private final AuthenticationProvider authenticationProvider;

//todo check if SecurityFilterChain is properly set

  /**
   *Check if this is correctly set and also if I should make
   * relation between isActive field and activated_user roleType
   .requestMatchers("/api/admin/**")
   .hasAuthority(String.valueOf(RoleType.admin))
   .requestMatchers("/api/offer/**")
   .hasAuthority(String.valueOf(RoleType.activated_user))
   */
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
        .csrf()
        .disable()
        .authorizeHttpRequests()
        .requestMatchers("/api/home/**","/api/auth/**")
        .permitAll()
        .requestMatchers("/api/admin/**")
        .hasAuthority(String.valueOf(RoleType.admin))
        .requestMatchers("/api/offer/**")
        .hasAuthority(String.valueOf(RoleType.activated_user))
        .anyRequest()
        .authenticated()
        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authenticationProvider(authenticationProvider)
        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

    return httpSecurity.build();
  }
}
