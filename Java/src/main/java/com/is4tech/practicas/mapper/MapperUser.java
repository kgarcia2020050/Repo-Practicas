package com.is4tech.practicas.mapper;

import com.is4tech.practicas.ProyectoParaPracticasApplication;
import com.is4tech.practicas.dto.UserDTO;
import com.is4tech.practicas.bo.Users;
import com.is4tech.practicas.exception.SyntaxErrorException;
import com.is4tech.practicas.service.ProfilesService;
import org.springframework.stereotype.Component;

@Component
public class MapperUser implements MapperDTO<UserDTO, Users> {

    private final ProfilesService profilesService;

    public MapperUser(ProfilesService profilesService) {
        this.profilesService = profilesService;
    }

    @Override
    public Users mapeo(UserDTO objeto) {
        if (!objeto.getEmail().matches(ProyectoParaPracticasApplication.EMAIL_EXPRESSION)) {
            throw new SyntaxErrorException("El email ingresado no es válido.");
        } else if (!objeto.getName().matches(ProyectoParaPracticasApplication.NAME_EXPRESSION)) {
            throw new SyntaxErrorException("El nombre no puede contener caracteres especiales, espacios dobles ni espacios al inicio o al final.");
        } else {
            Users model = new Users();
            model.setName(objeto.getName());
            model.setEmail(objeto.getEmail());
            model.setProfile(objeto.getProfile());
            model.setProfilesByProfile(profilesService.findById(objeto.getProfile()));
            model.setStatus((byte) 1);
            return model;
        }

    }
}
