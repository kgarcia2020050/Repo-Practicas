package com.is4tech.practicas.service;

import com.is4tech.practicas.dto.UserDTO;

import com.is4tech.practicas.exception.NotFoundException;
import com.is4tech.practicas.mapper.MapperUser;
import com.is4tech.practicas.bo.Users;
import com.is4tech.practicas.repository.UsersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final MapperUser mapper;

    private final UsersRepository usersRepository;

    private final ProfilesService profilesService;

    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserService(MapperUser mapper, UsersRepository usersRepository, ProfilesService profilesService) {
        this.mapper = mapper;
        this.usersRepository = usersRepository;
        this.profilesService = profilesService;
    }

    public Users findByName(String name) {
        return usersRepository.findByName(name);
    }

    public List<Users> findAll() {
        return usersRepository.findAll();
    }


    public void saveUser(UserDTO userdto) {
        Users model = mapper.mapeo(userdto);
        usersRepository.save(model);
        logger.info("Usuario guardado exitosamente");
    }

    public void editUser(Integer id, UserDTO userDTO) {
        Users model = usersRepository.findById(id).orElseThrow(() -> new NotFoundException("No se encuentra al usuario con el ID " + id));
        model.setName(userDTO.getName());
        model.setEmail(userDTO.getEmail());
        model.setStatus(userDTO.getStatus());
        model.setProfile(userDTO.getProfile());
        model.setProfilesByProfile(profilesService.findById(userDTO.getProfile()));
        usersRepository.save(model);
    }


}
