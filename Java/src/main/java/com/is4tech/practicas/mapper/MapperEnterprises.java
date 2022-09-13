package com.is4tech.practicas.mapper;

import com.is4tech.practicas.dto.EnterpriseDTO;
import com.is4tech.practicas.bo.Enterprises;
import org.springframework.stereotype.Component;

@Component
public class MapperEnterprises implements MapperDTO<EnterpriseDTO, Enterprises> {

    @Override
    public Enterprises mapeo(EnterpriseDTO objeto) {
        Enterprises model = new Enterprises();
        model.setName(objeto.getName());
        model.setStatus((byte) 1);
        return model;
    }


}
