package com.is4tech.practicas.service;

import com.is4tech.practicas.ProyectoParaPracticasApplication;
import com.is4tech.practicas.dto.UserDTO;

import com.is4tech.practicas.dto.UsersEnterprisesDTO;
import com.is4tech.practicas.exception.EmptyProfileException;
import com.is4tech.practicas.exception.ExistingRegisterException;
import com.is4tech.practicas.exception.NotFoundException;
import com.is4tech.practicas.mapper.MapperUser;
import com.is4tech.practicas.bo.Users;
import com.is4tech.practicas.bo.UsersEnterprises;
import com.is4tech.practicas.repository.UserEnterpriseRepository;
import com.is4tech.practicas.repository.UsersRepository;
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

    private static final String MESSAGE = "No se encuentra al usuario con el id ";


    public UserService(MapperUser mapper, UsersRepository usersRepository, ProfilesService profilesService,
                       UserEnterpriseRepository userEnterpriseRepository) {
        this.mapper = mapper;
        this.usersRepository = usersRepository;
        this.profilesService = profilesService;
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
            List<UsersEnterprisesDTO> enterprisesDTOS = this.userEnterpriseRepository.findAllDtoByUserId(users.get().getId());
            userDTO.setName(users.get().getName());
            userDTO.setProfile(users.get().getProfile());
            userDTO.setEmail(users.get().getEmail());
            userDTO.setStatus(users.get().getStatus() == (byte) 1);
            userDTO.setEmpresas(enterprisesDTOS);
            return userDTO;
        } else {
            throw new NotFoundException(MESSAGE + id);
        }
    }

    public Users userId(Integer id) {
        return this.usersRepository.findById(id).orElseThrow(() -> new NotFoundException(MESSAGE + id));
    }

    public void verification(Integer id, UserDTO userDTO) {
        UserDTO user = findById(id);
        if (!userDTO.getName().matches(ProyectoParaPracticasApplication.NAME_EXPRESSION)) {
            throw new EmptyProfileException("El nombre no puede contener caracteres especiales ni espacios dobles.");
        } else {
            if (!userDTO.getEmail().matches(ProyectoParaPracticasApplication.EMAIL_EXPRESSION)) {
                throw new ExistingRegisterException("El email ingresado no es valido.");
            } else if (user.getName().equals(userDTO.getName())) {
                editUser(id, userDTO);
            } else if (findByName(userDTO.getName()) != null || findByName(userDTO.getName().trim()) != null || findByName(userDTO.getName().toLowerCase()) != null || findByName(userDTO.getName().toUpperCase()) != null) {
                throw new ExistingRegisterException("Ya existe un usuario con el mismo nombre.");
            } else {
                editUser(id, userDTO);
            }
        }
    }

    public void saveUser(UserDTO userdto) {
        if (findByName(userdto.getName()) != null || findByName(userdto.getName().trim()) != null || findByName(userdto.getName().toLowerCase()) != null || findByName(userdto.getName().toUpperCase()) != null) {
            throw new ExistingRegisterException("Ya existe un usuario con el mismo nombre.");
        } else {
            Users bo = mapper.mapeo(userdto);
            bo = this.usersRepository.save(bo);
            if (userdto.getEmpresas() != null && !userdto.getEmpresas().isEmpty()) {
                List<UsersEnterprises> enterpirses = new ArrayList<>();
                for (int i = 0; i < userdto.getEmpresas().size(); i++) {
                    UsersEnterprises usersEnterprises = new UsersEnterprises();
                    usersEnterprises.setEnterpriseId(userdto.getEmpresas().get(i).getEnterpriseId());
                    usersEnterprises.setUserId(bo.getId());
                    usersEnterprises.setUserName(bo.getName());
                    usersEnterprises.setEnterpriseName(userdto.getEmpresas().get(i).getEnterpriseName());
                    enterpirses.add(usersEnterprises);
                }
                this.userEnterpriseRepository.saveAll(enterpirses);
            }
        }

    }

    public void deleteUserEnterpriseRegister(List<UsersEnterprisesDTO> misEmpresas) {
        for (UsersEnterprisesDTO misEmpresa : misEmpresas) {
            userEnterpriseRepository.deleteById(misEmpresa.getId());
        }
    }


    public void editUser(Integer id, UserDTO userDTO) {
        Users model = usersRepository.findById(id).orElseThrow(() -> new NotFoundException(MESSAGE + id));
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
            for (int i = 0; i < userDTO.getEmpresas().size(); i++) {
                UsersEnterprises usersEnterprises = new UsersEnterprises();
                if (userDTO.getEmpresas().get(i).getId() == null) {
                    usersEnterprises.setId(0);
                } else {
                    usersEnterprises.setId(userDTO.getEmpresas().get(i).getId());
                }
                usersEnterprises.setUserId(model.getId());
                usersEnterprises.setUserName(model.getName());
                usersEnterprises.setEnterpriseId(userDTO.getEmpresas().get(i).getEnterpriseId());
                usersEnterprises.setEnterpriseName(userDTO.getEmpresas().get(i).getEnterpriseName());
                enterpirses.add(usersEnterprises);
            }
            this.userEnterpriseRepository.saveAll(enterpirses);
        }
    }
}