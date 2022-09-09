package com.is4tech.practicas.controller;

import com.is4tech.practicas.dto.UserDTO;
import com.is4tech.practicas.bo.Users;
import com.is4tech.practicas.dto.UsersEnterprisesDTO;
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

    @PostMapping("/saveUser")
    public void saveUser(@RequestBody @Valid UserDTO userModel) {
        userService.saveUser(userModel);
    }

    @GetMapping("/pag/{id}")
    public Page<UsersEnterprisesDTO> findAllByUserId(@PathVariable("id") Integer id, @RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "4") int size,
                                                     @RequestParam(defaultValue = "enterpriseName") String order,
                                                     @RequestParam(defaultValue = "true") boolean asc) {
        Page<UsersEnterprisesDTO> empresas = userService.findAllByUserId(id, PageRequest.of(page, size, Sort.by(order)));
        if (!asc) {
            empresas = userService.findAllByUserId(id, PageRequest.of(page, size, Sort.by(order).descending()));
        }
        return empresas;

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
