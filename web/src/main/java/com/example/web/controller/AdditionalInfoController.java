package com.example.web.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/office")
public class AdditionalInfoController {
    //this controller could be used when user not approved
    //??loginController?? to identify if the  user should use this or UserController??
    //
}
