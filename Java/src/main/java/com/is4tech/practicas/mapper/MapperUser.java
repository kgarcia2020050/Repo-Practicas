package com.is4tech.practicas.mapper;

import com.is4tech.practicas.dto.UserDTO;
import com.is4tech.practicas.models.UsersModel;
import org.springframework.stereotype.Component;

@Component
public class MapperUser implements MapperDTO<UserDTO, UsersModel>{
    @Override
    public UsersModel mapeo(UserDTO objeto) {
        UsersModel model = new UsersModel();
        model.setEmail(objeto.getEmail());
        model.setName(objeto.getName());
        model.setStatus((byte)1);
        return model;
    }
}
