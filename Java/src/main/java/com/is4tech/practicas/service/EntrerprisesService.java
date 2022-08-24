package com.is4tech.practicas.service;

import com.is4tech.practicas.dto.EnterpriseDTO;
import com.is4tech.practicas.mapper.Mapper;
import com.is4tech.practicas.models.EnterprisesModel;
import com.is4tech.practicas.repository.EnterpriseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EntrerprisesService {

    private final Logger logger= LoggerFactory.getLogger(EntrerprisesService.class);

    private final EnterpriseRepository enterpriseRepository;

    private final Mapper mapper;

    public EntrerprisesService(EnterpriseRepository enterpriseRepository, Mapper mapper) {
        this.enterpriseRepository = enterpriseRepository;
        this.mapper = mapper;
    }




    public void save(EnterpriseDTO enterprisesModeDto){
        if(findByName(enterprisesModeDto.getName())!=null){

            logger.info("Ya existe");
        }else if(enterprisesModeDto.getName().length()>40){
            logger.info("La longitud maxima es de 40 caracteres");
        }else if(enterprisesModeDto.getName().length()<8){
            logger.info("La longitud minima es de 8 caracteress");
        }
        else{
            EnterprisesModel model=mapper.mapeo(enterprisesModeDto);
            enterpriseRepository.save(model);
        }

    }

    public EnterprisesModel findByName(String name){
        return enterpriseRepository.findByName(name);
    }


}
