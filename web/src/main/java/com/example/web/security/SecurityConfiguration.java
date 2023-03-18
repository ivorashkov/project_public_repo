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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

  private final JwtAuthFilter jwtAuthFilter;
  private final AuthenticationProvider authenticationProvider;
  private final LogoutHandler logoutHandler;//LogoutService implimentation. Spring will find that we have impl

  //todo check if SecurityFilterChain
  private static final String[] AUTH_WHITELIST = {
      "/api/home/**",
      "/api/auth/**",
  };

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
        .csrf()
        .disable()
        .authorizeHttpRequests()
        .requestMatchers(AUTH_WHITELIST)
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
        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
        .logout()
        .logoutUrl("/api/auth/logout") //using default Spring logout without implimentation
        .addLogoutHandler(logoutHandler)
        .logoutSuccessHandler(
            (request, response, authentication) ->
                SecurityContextHolder.clearContext()
        );

    return httpSecurity.build();
  }
}
