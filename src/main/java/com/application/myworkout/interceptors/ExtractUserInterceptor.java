package com.application.myworkout.interceptors;

import com.application.myworkout.domains.User;
import com.application.myworkout.services.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class ExtractUserInterceptor extends HandlerInterceptorAdapter {


  private final UserService userService;
  private User user;

  @Autowired
  public ExtractUserInterceptor(UserService userService) {
    this.userService = userService;
  }

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
      Object handler) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String email = authentication.getName();
    user = userService.findUserByEmail(email);
    return true;
  }

  public User getUser() {
    return user;
  }
}
