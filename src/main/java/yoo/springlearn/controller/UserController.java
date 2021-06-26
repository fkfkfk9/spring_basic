package yoo.springlearn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import yoo.springlearn.service.UserService;

@Controller
public class UserController {
     private final UserService userService;

     @Autowired
     public UserController(UserService userService){
         this.userService = userService;
     }
}
