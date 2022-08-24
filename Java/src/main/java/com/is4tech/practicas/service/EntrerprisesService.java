package com.is4tech.practicas.service;

import com.is4tech.practicas.dto.EnterpriseDTO;
import com.is4tech.practicas.exception.NotFoundException;
import com.is4tech.practicas.mapper.Mapper;
import com.is4tech.practicas.models.EnterprisesModel;
import com.is4tech.practicas.service.repository.EnterpriseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntrerprisesService {

    private final Logger logger= LoggerFactory.getLogger(EntrerprisesService.class);

    private final EnterpriseRepository enterpriseRepository;

    private final Mapper mapper;

    public EntrerprisesService(EnterpriseRepository enterpriseRepository, Mapper mapper) {
        this.enterpriseRepository = enterpriseRepository;
        this.mapper = mapper;
    }

    public EnterprisesModel findByName(String name){
        return enterpriseRepository.findByName(name);
    }

    public void save(EnterpriseDTO enterprisesModeDto){
        if(findByName(enterprisesModeDto.getName())!=null){
            logger.info("Ya existe una empresa con este nombre, intenta con uno diferente.");
        }else if(enterprisesModeDto.getName().length()>40){
            logger.info("La longitud maxima es de 40 caracteres.")          ;
        }else if(enterprisesModeDto.getName().length()<8){
            logger.info("La longitud minima es de 8 caracteres.");
        }
        else{
            EnterprisesModel model=mapper.mapeo(enterprisesModeDto);
            enterpriseRepository.save(model);
            logger.info("Empresa guardada exitosamente.");
        }

    }

    public List<EnterprisesModel> findAll(){
        return enterpriseRepository.findAll();
    }

    public void editEnterprise(Integer id,EnterpriseDTO enterpriseDTO){
        EnterprisesModel model=enterpriseRepository.findById(id).orElseThrow(()->new NotFoundException("No se encontro a la empresa con el ID "+id));
        model.setName(enterpriseDTO.getName());
        enterpriseRepository.save(model);
    }

}
