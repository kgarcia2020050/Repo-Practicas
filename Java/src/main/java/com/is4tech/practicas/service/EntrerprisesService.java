package com.is4tech.practicas.service;

import com.is4tech.practicas.dto.EnterpriseDTO;
import com.is4tech.practicas.exception.ExistingRegisterException;
import com.is4tech.practicas.mapper.MapperEnterprises;
import com.is4tech.practicas.bo.Enterprises;
import com.is4tech.practicas.repository.EnterpriseRepository;
import com.is4tech.practicas.repository.UserEnterpriseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EntrerprisesService {


    private final EnterpriseRepository enterpriseRepository;

    private final MapperEnterprises mapperEnterprises;

    private final UserEnterpriseRepository userEnterpriseRepository;

    public EntrerprisesService(EnterpriseRepository enterpriseRepository, MapperEnterprises mapperEnterprises, UserEnterpriseRepository userEnterpriseRepository) {
        this.enterpriseRepository = enterpriseRepository;
        this.mapperEnterprises = mapperEnterprises;
        this.userEnterpriseRepository = userEnterpriseRepository;
    }

    public Enterprises findByName(String name) {
        return enterpriseRepository.findByName(name);
    }

    public void save(EnterpriseDTO enterprisesModeDto) {
        if (findByName(enterprisesModeDto.getName()) != null || findByName(enterprisesModeDto.getName().trim()) != null || findByName(enterprisesModeDto.getName().toUpperCase()) != null || findByName(enterprisesModeDto.getName().toLowerCase()) != null) {
            throw new ExistingRegisterException("Ya existe una empresa con el mismo nombre.");
        } else {
            Enterprises model = mapperEnterprises.mapeo(enterprisesModeDto);
            enterpriseRepository.save(model);
        }
    }

    public List<Enterprises> findAll() {
        return enterpriseRepository.findAll();
    }

    public void deleteById(Integer id) {
        userEnterpriseRepository.deleteById(id);
    }

}
