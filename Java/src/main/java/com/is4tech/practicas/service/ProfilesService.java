package com.is4tech.practicas.service;

import com.is4tech.practicas.dto.ProfilesDTO;
import com.is4tech.practicas.exception.NotFoundException;
import com.is4tech.practicas.mapper.MapperProfile;
import com.is4tech.practicas.bo.Profiles;
import com.is4tech.practicas.repository.ProfileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class ProfilesService {

    private final Logger logger = LoggerFactory.getLogger(ProfilesService.class);
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

        Profiles model = mapperProfile.mapeo(profilesDTO);
        profileRepository.save(model);
        logger.info("Perfil guardado exitosamente.");
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
