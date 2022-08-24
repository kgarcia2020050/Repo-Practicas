package com.is4tech.practicas.controller;

import com.is4tech.practicas.dto.UserDTO;
import com.is4tech.practicas.models.UsersModel;
import com.is4tech.practicas.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/saveUser")
    public void saveUser(@RequestBody UserDTO userModel){
        userService.saveUser(userModel);
    }

    @GetMapping("/all")
    public List<UsersModel> findAll(){
        return userService.findAll();
    }

    @PutMapping("/editUser/{id}")
    public void editUser(@PathVariable("id")Integer id,@RequestBody UserDTO userDTO){
        userService.editUser(id,userDTO);
    }


}
