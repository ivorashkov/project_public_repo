package com.example.web.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

  //create -> for new registration
  //login
  //logout
  //if not approved -> /applicationForm
  //if approved -> /user?userId={id}
  //createOffer
  //seeOffers
}
