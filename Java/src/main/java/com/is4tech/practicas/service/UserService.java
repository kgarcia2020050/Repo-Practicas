package com.is4tech.practicas.service;

import com.is4tech.practicas.dto.UserDTO;
import com.is4tech.practicas.models.UsersModel;
import com.is4tech.practicas.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UsersRepository usersRepository;





}
