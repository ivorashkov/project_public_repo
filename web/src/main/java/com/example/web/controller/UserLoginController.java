package com.example.web.controller;

import com.example.web.model.dto.UserLoginDTO;
import com.example.web.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/login")
public class UserLoginController {
//TODO LAST
//    private final UserService userService;
//
//    @GetMapping("")
//    public String login() {
//    //todo validation stuff & response to FE
//        return null;
//    }
//
//    @PostMapping("")
//    public String login(UserLoginDTO user) {
//    //todo validation stuff & response to FE
//        return null;
//    }
}
