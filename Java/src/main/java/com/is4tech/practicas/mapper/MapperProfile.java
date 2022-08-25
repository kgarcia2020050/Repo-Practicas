package com.is4tech.practicas.mapper;

import com.is4tech.practicas.dto.ProfilesDTO;
import com.is4tech.practicas.bo.Profiles;
import org.springframework.stereotype.Component;

@Component
public class MapperProfile implements MapperDTO<ProfilesDTO, Profiles>{
    @Override
    public Profiles mapeo(ProfilesDTO objeto) {
        Profiles model=new Profiles();
        model.setName(objeto.getName());
        model.setStatus((byte) 1);
        return model;
    }
}
