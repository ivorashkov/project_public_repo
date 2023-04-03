package com.example.web.security;

import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationTrustResolver implements AuthenticationTrustResolver {

  private final AuthenticationTrustResolver delegate = new AuthenticationTrustResolverImpl();

  @Override
  public boolean isAnonymous(Authentication authentication) {
    return delegate.isAnonymous(authentication);
  }

  @Override
  public boolean isRememberMe(Authentication authentication) {
    return delegate.isRememberMe(authentication);
  }
}