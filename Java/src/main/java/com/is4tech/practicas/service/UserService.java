package com.is4tech.practicas.service;

import com.is4tech.practicas.dto.UserDTO;

import com.is4tech.practicas.mapper.MapperUser;
import com.is4tech.practicas.models.UsersModel;
import com.is4tech.practicas.service.repository.UsersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private MapperUser mapper;

    @Autowired
    private UsersRepository usersRepository;

    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    public UsersModel findByName(String name){
        return usersRepository.findByName(name);
    }

    public void saveUser(UserDTO userdto){
        if(findByName(userdto.getName())!= null){
            logger.info("Nombre de usuario ya utilizado, prueba con otro");
        }else if(userdto.getName().length()>40 || userdto.getName().length()<8){
            logger.info("La longitud máxima es de 40 caracteres");
        }
        else {
            UsersModel model = mapper.mapeo(userdto);
            usersRepository.save(model);
            logger.info("Usuario guardado exitosamente");
        }
    }

    
    public List<UsersModel> findAll(){
        return usersRepository.findAll();
    }
    



}
