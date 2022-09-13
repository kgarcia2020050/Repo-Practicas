package com.is4tech.practicas.service;

import com.is4tech.practicas.dto.EnterpriseDTO;
import com.is4tech.practicas.exception.ExistingRegisterException;
import com.is4tech.practicas.exception.InformationNotChangedException;
import com.is4tech.practicas.exception.NotFoundException;
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

    private static final String MESSAGE = "No se encuentra al perfil con el id ";

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

    public void verification(Integer id, EnterpriseDTO enterpriseDTO) {
        Enterprises entereprise = enterpriseRepository.findById(id).orElseThrow(() -> new NotFoundException(MESSAGE + id));
        Byte status = (enterpriseDTO.isStatus() ? (byte) 1 : (byte) 0);
        if (enterpriseDTO.getName().equals(entereprise.getName()) && entereprise.getStatus().equals(status)) {
            throw new InformationNotChangedException("No has cambiado la información de la empresa.");
        } else if (entereprise.getName().equals(enterpriseDTO.getName())) {
            editEnterprise(id, enterpriseDTO);
        } else if (findByName(enterpriseDTO.getName()) != null && findByName(enterpriseDTO.getName().trim()) != null && findByName(enterpriseDTO.getName().toUpperCase()) != null && findByName(enterpriseDTO.getName().toLowerCase()) != null) {
            throw new ExistingRegisterException("Ya existe una empresa con el mismo nombre.");
        } else {
            editEnterprise(id, enterpriseDTO);
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
