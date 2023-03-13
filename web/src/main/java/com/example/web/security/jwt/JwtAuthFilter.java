package com.example.web.security.jwt;

import com.example.web.constant.SpringSecurityConstants;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

  private final JwtService jwtService;
  private final UserDetailsService userDetailsService;

  @Override
  protected void doFilterInternal(
      @NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull FilterChain filterChain
  ) throws ServletException, IOException {

    final String authHeader = request.getHeader(SpringSecurityConstants.JWT_REQUEST_HEADER);
    final String jwtToken;
    final String userEmail;

    //user is not existing or is not logged in yet.
    if (authHeader == null || !authHeader.startsWith(SpringSecurityConstants.JWT_TOKEN_BEARER)) {
      filterChain.doFilter(request, response);
      return;
    }
    jwtToken = authHeader.substring(SpringSecurityConstants.JWT_TOKEN_BEARER.length());
    userEmail = this.jwtService.extractUsername(jwtToken);

    if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

      if (jwtService.isTokenValid(jwtToken, userDetails)) {
        UsernamePasswordAuthenticationToken authToken =
            new UsernamePasswordAuthenticationToken(
//                userDetails,
                userDetails.getUsername(),
                userDetails.getPassword(),
                userDetails.getAuthorities()
            );
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authToken);
      }
    }
    filterChain.doFilter(request, response);
  }
}
