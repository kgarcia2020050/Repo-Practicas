package com.is4tech.practicas.controller;

import com.is4tech.practicas.dto.UserDTO;
import com.is4tech.practicas.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/saveUser")
    public void saveUser(@RequestBody UserDTO userModel){
        userService.saveUser(userModel);
    }


}
