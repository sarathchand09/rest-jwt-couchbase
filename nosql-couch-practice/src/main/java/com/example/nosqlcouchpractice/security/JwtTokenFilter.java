package com.example.nosqlcouchpractice.security;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

@Component
@Slf4j
public class JwtTokenFilter extends GenericFilterBean {

  private JwtTokenProvider jwtTokenProvider;

  public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
    this.jwtTokenProvider = jwtTokenProvider;
  }

  @Override
  public void doFilter(
      ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
      throws IOException, ServletException {
    String token = jwtTokenProvider.resolveToken((HttpServletRequest) servletRequest);
    if (token != null && jwtTokenProvider.validateToken(token)) {
      SecurityContextHolder.getContext()
          .setAuthentication(jwtTokenProvider.getAuthentication(token));
    } else {
      SecurityContextHolder.getContext().setAuthentication(null);
    }
    filterChain.doFilter(servletRequest, servletResponse);
  }
}
