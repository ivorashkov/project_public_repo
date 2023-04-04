package com.example.web.security;

import com.example.web.model.enums.RoleType;
import com.example.web.security.corsFilter.CorsFilter;
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
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

  private final CustomAuthenticationTrustResolver customAuthenticationTrustResolver;
  private final JwtAuthFilter jwtAuthFilter;
  private final CorsFilter corsFilter;
  private final AuthenticationProvider authenticationProvider;
  private final LogoutHandler logoutHandler;//LogoutService implimentation. Spring will find that we have impl

  private static final String[] AUTH_WHITELIST = {
      "/api/home/offers",
      "/api/home/country/list",
      "/api/home/city/list",
      "/api/home/{id}",
      "/api/auth/signup",
      "/api/auth/authenticate",
      "/api/auth/logout",
      "/api/user/upload/all/*"
  };

  private static final String[] AUTH_ADMIN_LIST = {
      "/api/admin/request"
  };

  private static final String[] AUTH_ACTIVE_USER_LIST = {
      "/api/user/upload/all",
      "/api/offer/save",
      "/api/offer/edit",
      "/api/offer/delete",
      "/api/offer/create/",
      "/api/offer/finish/"
  };

  private static final String LOGOUT_URL = "/api/auth/logout";

//  @Bean
//  public AuthenticationTrustResolver authenticationTrustResolver() {
//    return CustomAuthenticationTrustResolver();
//  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    //first step disable csrf
    httpSecurity
        .csrf().disable()
        //authorize HTTP REQUESTS
        .authorizeHttpRequests( authorize ->
            authorize
                .requestMatchers(AUTH_WHITELIST)
                .permitAll()
                .requestMatchers(AUTH_ADMIN_LIST)
                .hasAuthority(String.valueOf(RoleType.admin))
                .requestMatchers(AUTH_ACTIVE_USER_LIST)
                .hasAuthority(String.valueOf(RoleType.active))
                .anyRequest()
                .authenticated()
        )
        //SET SESSION STATELESS
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authenticationProvider(authenticationProvider)
        .addFilterAt(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
        .addFilterAfter(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
        .addFilterAt(corsFilter, LogoutFilter.class)
        .exceptionHandling()
        .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
        .accessDeniedHandler(new CustomAccessDeniedHandler())
        .and()
        .logout()
        .logoutUrl(LOGOUT_URL) //using default Spring logout without implimentation
        .addLogoutHandler(logoutHandler)
        .logoutSuccessHandler(
            (request, response, authentication) ->
                SecurityContextHolder.clearContext()
        );

    return httpSecurity.build();

  }
}
