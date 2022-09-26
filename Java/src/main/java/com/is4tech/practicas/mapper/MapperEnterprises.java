package com.is4tech.practicas.mapper;

import com.is4tech.practicas.ProyectoParaPracticasApplication;
import com.is4tech.practicas.dto.EnterpriseDTO;
import com.is4tech.practicas.bo.Enterprises;
import com.is4tech.practicas.exception.SyntaxErrorException;
import org.springframework.stereotype.Component;

@Component
public class MapperEnterprises implements MapperDTO<EnterpriseDTO, Enterprises> {

    @Override
    public Enterprises mapeo(EnterpriseDTO objeto) {
        if (!objeto.getName().matches(ProyectoParaPracticasApplication.NAME_EXPRESSION)) {
            throw new SyntaxErrorException("El nombre no puede contener caracteres especiales ni espacios dobles.");
        } else {
            Enterprises model = new Enterprises();
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
