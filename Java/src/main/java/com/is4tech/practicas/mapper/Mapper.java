package com.is4tech.practicas.mapper;

import com.is4tech.practicas.dto.EnterpriseDTO;
import com.is4tech.practicas.models.EnterprisesModel;
import org.springframework.stereotype.Component;

@Component
public class Mapper implements MapperDTO<EnterpriseDTO, EnterprisesModel>{

    @Override
    public EnterprisesModel mapeo(EnterpriseDTO objeto) {
        EnterprisesModel model=new EnterprisesModel();
        model.setName(objeto.getName());
        return model;
    }


}
