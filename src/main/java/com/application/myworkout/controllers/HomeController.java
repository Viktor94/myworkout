package com.application.myworkout.controllers;

import com.application.myworkout.services.UserService;
import com.application.myworkout.services.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

  private UserService userService;
  private WorkoutService workoutService;

  @Autowired
  public HomeController(UserService userService,
      WorkoutService workoutService) {
    this.userService = userService;
    this.workoutService = workoutService;
  }

  @GetMapping("/")
  public String home() {
    return "index";
  }

  @GetMapping("/login")
  public String login (Model model) {

    return "login";
  }
}
