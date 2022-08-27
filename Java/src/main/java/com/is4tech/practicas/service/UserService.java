package com.is4tech.practicas.service;

import com.is4tech.practicas.dto.UserDTO;

import com.is4tech.practicas.exception.NotFoundException;
import com.is4tech.practicas.mapper.MapperUser;
import com.is4tech.practicas.bo.Users;
import com.is4tech.practicas.repository.UsersRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private final MapperUser mapper;

    private final UsersRepository usersRepository;

    private final ProfilesService profilesService;

    private final UserEnterpriseService userEnterpriseService;


    public UserService(MapperUser mapper, UsersRepository usersRepository, ProfilesService profilesService, UserEnterpriseService userEnterpriseService) {
        this.mapper = mapper;
        this.usersRepository = usersRepository;
        this.profilesService = profilesService;
        this.userEnterpriseService = userEnterpriseService;
    }

    public Users findByName(String name) {
        return usersRepository.findByName(name);
    }

    public Page<Users> findAll(Pageable pageable) {
        return usersRepository.findAll(pageable);
    }

    public Users findById(Integer id) {
        return usersRepository.findById(id).orElseThrow(() -> new NotFoundException("No se encuentra al usuario con el ID " + id));
    }


    public void saveUser(UserDTO userdto) {
        Users model = mapper.mapeo(userdto);
        usersRepository.save(model);
    }

    public void editUser(Integer id, UserDTO userDTO) {
        Users model = usersRepository.findById(id).orElseThrow(() -> new NotFoundException("No se encuentra al usuario con el ID " + id));
        model.setName(userDTO.getName());
        model.setEmail(userDTO.getEmail());
        if (userDTO.isStatus()) {
            model.setStatus((byte) 1);
        } else {
            model.setStatus((byte) 0);
        }
        model.setProfile(userDTO.getProfile());
        model.setProfilesByProfile(profilesService.findById(userDTO.getProfile()));
        usersRepository.save(model);
    }


}
