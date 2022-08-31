package com.is4tech.practicas.controller;

import com.is4tech.practicas.dto.UserDTO;
import com.is4tech.practicas.bo.Users;
import com.is4tech.practicas.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        return userService.usuarioId(id);
    }

    @PostMapping("/saveUser")
    public ResponseEntity<String> saveUser(@RequestBody @Valid UserDTO userModel) {
        if (userService.findByName(userModel.getName()) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ya existe un usuario con el mismo nombre.");
        } else if (userModel.getProfile() == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Debes asignarte un perfil.");
        } else {
            userService.saveUser(userModel);
            return null;
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
    public ResponseEntity<String> editUser(@PathVariable("id") Integer id, @RequestBody UserDTO userDTO) {
        Users user = userService.usuarioId(id);
        Byte status = (userDTO.isStatus() ? (byte) 1 : (byte) 0);
        String name = userDTO.getName();
        String emai = userDTO.getEmail();
        Integer profile = user.getProfile();
        if (name.equals(user.getName()) && status.equals(user.getStatus()) &&
                emai.equals(userDTO.getEmail()) && profile.equals(userDTO.getProfile())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No has cambiado la informacion del usuario.");
        } else if (name.equals(user.getName())) {
            userService.editUser(id, userDTO);
        } else if (userService.findByName(userDTO.getName()) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ya existe un usuario con el mismo nombre.");
        } else {
            userService.editUser(id, userDTO);
        }
        return null;
    }


}
