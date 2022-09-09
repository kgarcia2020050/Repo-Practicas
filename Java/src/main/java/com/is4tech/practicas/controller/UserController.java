package com.is4tech.practicas.controller;

import com.is4tech.practicas.dto.UserDTO;
import com.is4tech.practicas.bo.Users;
import com.is4tech.practicas.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@CrossOrigin("http://localhost:4200/")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/{id}")
    public Users findById(@PathVariable("id") Integer id) {
        return userService.userId(id);
    }

    @GetMapping("/users/{id}")
    public UserDTO findBy(@PathVariable("id") Integer id) {
        return userService.findById(id);
    }

    @PostMapping("/saveUser")
    public void saveUser(@RequestBody @Valid UserDTO userModel) {
        userService.saveUser(userModel);
    }

    @GetMapping("/all")
    public Page<Users> findAll(@RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "6") int size,
                               @RequestParam(defaultValue = "name") String order,
                               @RequestParam(defaultValue = "true") boolean asc) {
        Page<Users> users = userService.findAll(PageRequest.of(page, size, Sort.by(order)));
        if (!asc) {
            users = userService.findAll(PageRequest.of(page, size, Sort.by(order).descending()));
        }
        return users;
    }

    @PutMapping("/editUser/{id}")
    public void editUser(@PathVariable("id") Integer id, @RequestBody @Valid UserDTO userDTO) {
        userService.verification(id, userDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable("id") Integer id) {
        this.userService.deleteUserEnterpriseRegister(id);
    }


}
