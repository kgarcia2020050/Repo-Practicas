package com.is4tech.practicas.mapper;

import com.is4tech.practicas.dto.ProfilesDTO;
import com.is4tech.practicas.models.ProfilesModel;
import org.springframework.stereotype.Component;

@Component
public class MapperProfile implements MapperDTO<ProfilesDTO, ProfilesModel>{
    @Override
    public ProfilesModel mapeo(ProfilesDTO objeto) {
        ProfilesModel model=new ProfilesModel();
        model.setName(objeto.getName());
        model.setStatus((byte) 1);
        return model;
    }
}
