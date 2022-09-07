package com.is4tech.practicas.service;

import com.is4tech.practicas.dto.ProfilesDTO;
import com.is4tech.practicas.exception.ExistingRegisterException;
import com.is4tech.practicas.exception.InformationNotChangedException;
import com.is4tech.practicas.exception.NotFoundException;
import com.is4tech.practicas.mapper.MapperProfile;
import com.is4tech.practicas.bo.Profiles;
import com.is4tech.practicas.repository.ProfileRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProfilesService {

    private final ProfileRepository profileRepository;

    private final MapperProfile mapperProfile;


    public ProfilesService(ProfileRepository profileRepository, MapperProfile mapperProfile) {
        this.profileRepository = profileRepository;
        this.mapperProfile = mapperProfile;
    }


    public Profiles findByName(String name) {
        return profileRepository.findByName(name);
    }


    public Page<Profiles> findAll(Pageable pageable) {
        return profileRepository.findAll(pageable);
    }

    public Profiles findById(Integer id) {
        return profileRepository.findById(id).orElseThrow(NotFoundException::new);
    }


    public void saveProfile(ProfilesDTO profilesDTO) {
        if (findByName(profilesDTO.getName()) != null) {
            throw new ExistingRegisterException("Ya existe un perfil con el mismo nombre.");
        } else {
            Profiles model = mapperProfile.mapeo(profilesDTO);
            profileRepository.save(model);
        }
    }


    public List<Profiles> findAllByStatus(Byte status){
        return this.profileRepository.findAllByStatus(status);
    }


    public void verification(Integer id, ProfilesDTO profilesDTO) {
        Profiles profile = findById(id);
        Byte status = (profilesDTO.isStatus() ? (byte) 1 : (byte) 0);
        if (profilesDTO.getName().equals(profile.getName()) && profile.getStatus().equals(status)) {
            throw new InformationNotChangedException("No has cambiado la información del perfil.");
        } else if (profilesDTO.getName().equals(profile.getName())) {
            editProfile(id, profilesDTO);
        } else if (findByName(profilesDTO.getName()) != null) {
            throw new ExistingRegisterException("Ya existe un perfil con el mismo nombre.");
        } else {
            editProfile(id, profilesDTO);
        }
    }

    public void editProfile(Integer id, ProfilesDTO profilesDTO) {
        Profiles model = profileRepository.findById(id).orElseThrow(NotFoundException::new);
        model.setName(profilesDTO.getName());
        if (profilesDTO.isStatus()) {
            model.setStatus((byte) 1);
        } else {
            model.setStatus((byte) 0);
        }
        profileRepository.save(model);
    }

}
