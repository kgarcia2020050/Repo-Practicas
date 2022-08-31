package com.is4tech.practicas.service;

import com.is4tech.practicas.dto.EnterpriseDTO;
import com.is4tech.practicas.exception.NotFoundException;
import com.is4tech.practicas.mapper.MapperEnterprises;
import com.is4tech.practicas.bo.Enterprises;
import com.is4tech.practicas.repository.EnterpriseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class EntrerprisesService {


    private final EnterpriseRepository enterpriseRepository;

    private final MapperEnterprises mapperEnterprises;

    public EntrerprisesService(EnterpriseRepository enterpriseRepository, MapperEnterprises mapperEnterprises) {
        this.enterpriseRepository = enterpriseRepository;
        this.mapperEnterprises = mapperEnterprises;
    }

    public Enterprises findByName(String name) {
        return enterpriseRepository.findByName(name);
    }

    public void save(EnterpriseDTO enterprisesModeDto) {
        Enterprises model = mapperEnterprises.mapeo(enterprisesModeDto);
        enterpriseRepository.save(model);
    }

    public Page<Enterprises> findAll(Pageable pageable) {
        return enterpriseRepository.findAll(pageable);
    }

    public void editEnterprise(Integer id, EnterpriseDTO enterpriseDTO) {
        Enterprises model = enterpriseRepository.findById(id).orElseThrow(NotFoundException::new);
        model.setName(enterpriseDTO.getName());
        enterpriseRepository.save(model);
    }


}
