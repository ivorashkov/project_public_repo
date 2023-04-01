package com.example.web.security.corsFilter;

import com.example.web.constant.CorsFilterConstants;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CorsFilter implements Filter {

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {

  }

  @Override
  public void doFilter(
      ServletRequest servletRequest,
      ServletResponse servletResponse,
      FilterChain filterChain
  ) throws IOException, ServletException {
    HttpServletResponse response = (HttpServletResponse) servletResponse;

    response.setHeader(CorsFilterConstants.HEADER_ACCESS_CONTROL_ALLOW_ORIGIN
        , CorsFilterConstants.VALUE_WILD_CARD);
    response.setHeader(CorsFilterConstants.HEADER_ACCESS_CONTROL_ALLOW_ORIGIN
        , CorsFilterConstants.VALUE_WILD_CARD);
    response.setHeader(CorsFilterConstants.HEADER_ACCESS_CONTROL_ALLOW_METHODS,
        CorsFilterConstants.VALUE_METHODS);
    response.setHeader(CorsFilterConstants.HEADER_ACCESS_CONTROL_ALLOW_HEADERS,
        CorsFilterConstants.VALUE_WILD_CARD);
    response.setHeader(CorsFilterConstants.HEADER_ACCESS_CONTROL_MAX_AGE,
        CorsFilterConstants.VALUE_MAX_AGE);
    response.setHeader(CorsFilterConstants.HEADER_ACCESS_CONTROL_EXPOSE_HEADERS
        , CorsFilterConstants.VALUE_WILD_CARD);
    response.setHeader(CorsFilterConstants.HEADER_ACCESS_CONTROL_ACCEPT_ENCODING,
        CorsFilterConstants.VALUE_WILD_CARD);


    filterChain.doFilter(servletRequest, servletResponse);
  }

  @Override
  public void destroy() {

  }
}