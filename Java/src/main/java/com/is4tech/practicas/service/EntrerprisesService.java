package com.is4tech.practicas.service;

import com.is4tech.practicas.dto.EnterpriseDTO;
import com.is4tech.practicas.exception.NotFoundException;
import com.is4tech.practicas.mapper.Mapper;
import com.is4tech.practicas.bo.Enterprises;
import com.is4tech.practicas.repository.EnterpriseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class EntrerprisesService {


    private final EnterpriseRepository enterpriseRepository;

    private final Mapper mapper;

    public EntrerprisesService(EnterpriseRepository enterpriseRepository, Mapper mapper) {
        this.enterpriseRepository = enterpriseRepository;
        this.mapper = mapper;
    }

    public Enterprises findByName(String name){
        return enterpriseRepository.findByName(name);
    }

    public void save(EnterpriseDTO enterprisesModeDto){
            Enterprises model=mapper.mapeo(enterprisesModeDto);
            enterpriseRepository.save(model);

    }

    public Page<Enterprises> findAll(Pageable pageable){
        return enterpriseRepository.findAll(pageable);
    }

    public void editEnterprise(Integer id,EnterpriseDTO enterpriseDTO){
        Enterprises model=enterpriseRepository.findById(id).orElseThrow(()->new NotFoundException("No se encontro a la empresa con el ID "+id));
        model.setName(enterpriseDTO.getName());
        enterpriseRepository.save(model);
    }




}
