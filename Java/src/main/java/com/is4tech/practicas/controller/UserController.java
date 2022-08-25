package com.is4tech.practicas.controller;

import com.is4tech.practicas.dto.UserDTO;
import com.is4tech.practicas.bo.Users;
import com.is4tech.practicas.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/saveUser")
    public ResponseEntity<String> saveUser(@RequestBody @Valid UserDTO userModel) {
        if (userService.findByName(userModel.getName()) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ya existe un usuario con el mismo nombre.");
        } else {
            userService.saveUser(userModel);
            return null;
        }
    }

    @GetMapping("/all")
    public List<Users> findAll() {
        return userService.findAll();
    }

    @PutMapping("/editUser/{id}")
    public ResponseEntity<String> editUser(@PathVariable("id") Integer id, @RequestBody UserDTO userDTO) {
        if (userService.findByName(userDTO.getName()) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ya existe un usuario con el mismo nombre.");
        } else {
            userService.editUser(id, userDTO);
            return null;
        }
    }


}
