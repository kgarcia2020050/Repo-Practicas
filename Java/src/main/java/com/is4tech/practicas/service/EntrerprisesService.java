package com.is4tech.practicas.service;

import com.is4tech.practicas.ProyectoParaPracticasApplication;
import com.is4tech.practicas.dto.EnterpriseDTO;
import com.is4tech.practicas.exception.ExistingRegisterException;
import com.is4tech.practicas.exception.NotFoundException;
import com.is4tech.practicas.mapper.MapperEnterprises;
import com.is4tech.practicas.bo.Enterprises;
import com.is4tech.practicas.repository.EnterpriseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EntrerprisesService {


    private final EnterpriseRepository enterpriseRepository;


    private final MapperEnterprises mapperEnterprises;


    private static final String MESSAGE = "No se encuentra al perfil con el id ";

    public EntrerprisesService(EnterpriseRepository enterpriseRepository, MapperEnterprises mapperEnterprises) {
        this.enterpriseRepository = enterpriseRepository;
        this.mapperEnterprises = mapperEnterprises;
    }

    public Enterprises findByName(String name) {
        return enterpriseRepository.findByName(name);
    }

    public Enterprises findById(Integer id) {
        return enterpriseRepository.findById(id).orElseThrow(() -> new NotFoundException("No se encuentra la empresa con el ID " + id));
    }


    public void save(EnterpriseDTO enterprisesModeDto) {
        if (!enterprisesModeDto.getName().matches(ProyectoParaPracticasApplication.NAME_EXPRESSION)) {
            throw new ExistingRegisterException("El nombre no puede contener caracteres especiales ni espacios dobles.");
        } else {
            if (findByName(enterprisesModeDto.getName()) != null || findByName(enterprisesModeDto.getName().trim()) != null || findByName(enterprisesModeDto.getName().toUpperCase()) != null || findByName(enterprisesModeDto.getName().toLowerCase()) != null) {
                throw new ExistingRegisterException("Ya existe una empresa con el mismo nombre.");
            } else {
                Enterprises model = mapperEnterprises.mapeo(enterprisesModeDto);
                enterpriseRepository.save(model);
            }
        }
    }

    public List<Enterprises> findAll() {
        return enterpriseRepository.findAll();
    }


    public Page<Enterprises> findAllPagination(Pageable pageable) {
        return enterpriseRepository.findAll(pageable);
    }

    public void verification(Integer id, EnterpriseDTO enterpriseDTO) {
        if (!enterpriseDTO.getName().matches(ProyectoParaPracticasApplication.NAME_EXPRESSION)) {
            throw new ExistingRegisterException("El nombre no puede contener caracteres especiales ni espacios dobles.");
        } else {
            Enterprises entereprise = enterpriseRepository.findById(id).orElseThrow(() -> new NotFoundException(MESSAGE + id));
            if (entereprise.getName().equals(enterpriseDTO.getName())) {
                editEnterprise(id, enterpriseDTO);
            } else if (findByName(enterpriseDTO.getName()) != null && findByName(enterpriseDTO.getName().trim()) != null && findByName(enterpriseDTO.getName().toUpperCase()) != null && findByName(enterpriseDTO.getName().toLowerCase()) != null) {
                throw new ExistingRegisterException("Ya existe una empresa con el mismo nombre.");
            } else {
                editEnterprise(id, enterpriseDTO);
            }
        }
    }

    public void editEnterprise(Integer id, EnterpriseDTO enterpriseDTO) {
        Enterprises model = enterpriseRepository.findById(id).orElseThrow(() -> new NotFoundException(MESSAGE + id));
        model.setName(enterpriseDTO.getName());
        if (enterpriseDTO.isStatus()) {
            model.setStatus((byte) 1);
        } else {
            model.setStatus((byte) 0);
        }
        enterpriseRepository.save(model);
    }


}