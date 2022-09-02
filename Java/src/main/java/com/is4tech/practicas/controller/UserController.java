package com.is4tech.practicas.controller;

import com.is4tech.practicas.dto.UserDTO;
import com.is4tech.practicas.bo.Users;
import com.is4tech.practicas.exception.EmptyProfileException;
import com.is4tech.practicas.exception.ExistingRegisterException;
import com.is4tech.practicas.exception.InformationNotChangedException;
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
        if (userService.findByName(userModel.getName()) != null) {
            throw new ExistingRegisterException("Ya existe un usuario con el mismo nombre.");
        } else if (userModel.getProfile() == 0) {
          throw new EmptyProfileException("Debes asignarte un perfil.");
        } else {
            userService.saveUser(userModel);
        }
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
        UserDTO user = userService.findById(id);
        if (user.getName().equals(userDTO.getName()) && userDTO.isStatus() == user.isStatus() &&
                user.getEmail().equals(userDTO.getEmail()) && user.getProfile().equals(userDTO.getProfile())) {
            throw new InformationNotChangedException("No has cambiado la informacion del usuario.");
        } else if (user.getName().equals(userDTO.getName())) {
            userService.editUser(id, userDTO);
        } else if (userService.findByName(userDTO.getName()) != null) {
            throw new ExistingRegisterException("Ya existe un usuario con el mismo nombre.");
        } else {
            userService.editUser(id, userDTO);
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable("id")Integer id){
        this.userService.deleteUserEnterpriseRegister(id);
    }



}
