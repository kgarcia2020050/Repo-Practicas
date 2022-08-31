package com.is4tech.practicas.service;

import com.is4tech.practicas.dto.UserDTO;

import com.is4tech.practicas.dto.UsersEnterprisesDTO;
import com.is4tech.practicas.exception.NotFoundException;
import com.is4tech.practicas.mapper.MapperUser;
import com.is4tech.practicas.bo.Users;
import com.is4tech.practicas.bo.UsersEnterprises;
import com.is4tech.practicas.repository.UserEnterpriseRepository;
import com.is4tech.practicas.repository.UsersRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class UserService {

    private final MapperUser mapper;

    private final UsersRepository usersRepository;

    private final ProfilesService profilesService;
    private final UserEnterpriseRepository userEnterpriseRepository;
    private final UserEnterpriseService userEnterpriseService;


    public UserService(MapperUser mapper, UsersRepository usersRepository, ProfilesService profilesService, UserEnterpriseService userEnterpriseService, UserEnterpriseRepository userEnterpriseRepository) {
        this.mapper = mapper;
        this.usersRepository = usersRepository;
        this.profilesService = profilesService;
        this.userEnterpriseService = userEnterpriseService;
        this.userEnterpriseRepository = userEnterpriseRepository;
    }

    public Users findByName(String name) {
        return usersRepository.findByName(name);
    }

    public Page<Users> findAll(Pageable pageable) {
        return usersRepository.findAll(pageable);
    }

    public UserDTO findById(Integer id) {
        Optional<Users> users = this.usersRepository.findById(id);
        UserDTO userDTO = new UserDTO();

        if (users.isPresent()) {
            BeanUtils.copyProperties(users, userDTO);
            List<UsersEnterprisesDTO> enterprisesDTOS = this.userEnterpriseRepository.findAllDtoByUserId(users.get().getId());
            userDTO.setEmpresas(enterprisesDTOS);
            return userDTO;
        } else {
            throw new NotFoundException();
        }

    }

    public Users usuarioId(Integer id){
        return this.usersRepository.findById(id).orElseThrow(NotFoundException::new);
    }


    public void saveUser(UserDTO userdto) {
        Users bo = mapper.mapeo(userdto);
        bo = this.usersRepository.save(bo);

        if (userdto.getEmpresas() != null && !userdto.getEmpresas().isEmpty()) {
            List<UsersEnterprises> enterpirses = new ArrayList<>();
            for (int i=0;i<userdto.getEmpresas().size();i++) {
                UsersEnterprises usersEnterprises = new UsersEnterprises();
                usersEnterprises.setEnterpriseId(userdto.getEmpresas().get(i).getEnterpriseId());
                usersEnterprises.setUserId(bo.getId());
                usersEnterprises.setUserName(bo.getName());
                usersEnterprises.setEnterpriseName(userdto.getEmpresas().get(i).getEnterpriseName());
                enterpirses.add(usersEnterprises);
            }
            this.userEnterpriseRepository.saveAll(enterpirses);
        }

//        if (!userdto.getEmpresas().isEmpty()) {
//            userEnterpriseService.save(model, userdto.getEmpresas());
//        }
    }

    public void editUser(Integer id, UserDTO userDTO) {
        Users model = usersRepository.findById(id).orElseThrow(NotFoundException::new);
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

        if (userDTO.getEmpresas() != null && !userDTO.getEmpresas().isEmpty()) {
            List<UsersEnterprises> enterpirses = new ArrayList<>();
            for (UsersEnterprisesDTO usersEnterprisesDTO: userDTO.getEmpresas()) {
                UsersEnterprises usersEnterprises = new UsersEnterprises();
                BeanUtils.copyProperties(usersEnterprisesDTO, usersEnterprises);
                enterpirses.add(usersEnterprises);
            }
            this.userEnterpriseRepository.saveAll(enterpirses);
        }

        /*
        if (!userDTO.getEmpresas().isEmpty()) {
            userEnterpriseService.edit(model, userDTO.getEmpresas());
        }*/


    }


}