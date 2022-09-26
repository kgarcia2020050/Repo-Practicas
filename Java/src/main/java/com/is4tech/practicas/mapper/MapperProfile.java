package com.is4tech.practicas.mapper;

import com.is4tech.practicas.ProyectoParaPracticasApplication;
import com.is4tech.practicas.dto.ProfilesDTO;
import com.is4tech.practicas.bo.Profiles;
import com.is4tech.practicas.exception.SyntaxErrorException;
import org.springframework.stereotype.Component;

@Component
public class MapperProfile implements MapperDTO<ProfilesDTO, Profiles> {
    @Override
    public Profiles mapeo(ProfilesDTO objeto) {
        if (!objeto.getName().matches(ProyectoParaPracticasApplication.NAME_EXPRESSION)) {
            throw new SyntaxErrorException("El nombre no puede contener caracteres especiales, espacios dobles ni espacios al inicio o al final.");
        } else {
            Profiles model = new Profiles();
            model.setName(objeto.getName());
            if (objeto.isPermission()) {
                model.setPermission((byte) 1);
            } else {
                model.setPermission((byte) 0);
            }
            model.setStatus((byte) 1);
            return model;
        }
    }
}
