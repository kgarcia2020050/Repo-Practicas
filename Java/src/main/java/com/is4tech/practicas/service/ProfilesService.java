package com.is4tech.practicas.service;

import com.is4tech.practicas.dto.ProfilesDTO;
import com.is4tech.practicas.exception.NotFoundException;
import com.is4tech.practicas.mapper.MapperProfile;
import com.is4tech.practicas.models.ProfilesModel;
import com.is4tech.practicas.service.repository.ProfileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfilesService {

    private final Logger logger= LoggerFactory.getLogger(ProfilesService.class);
    private final ProfileRepository profileRepository;

    private final MapperProfile mapperProfile;

    public ProfilesService(ProfileRepository profileRepository, MapperProfile mapperProfile) {
        this.profileRepository = profileRepository;
        this.mapperProfile = mapperProfile;
    }



    public ProfilesModel findByName(String name){
        return profileRepository.findByName(name);
    }


    public List<ProfilesModel> findAll(){
        return profileRepository.findAll();
    }

    public ProfilesModel findById(Integer id){
        return profileRepository.findById(id).orElseThrow(()->new NotFoundException("No se encuentra al perfil con el ID "+id));
    }


    public void saveProfile(ProfilesDTO profilesDTO){
        if(findByName(profilesDTO.getName())!=null){
            logger.info("Ya existe un perfil con este nombre, intenta con uno diferente.");
        }else if(profilesDTO.getName().length()>40){
            logger.info("La longitud maxima es de 40 caracteres.");
        }else if(profilesDTO.getName().length()<8){
            logger.info("La longitud minima es de 8 caracteres.");
        }else{
            ProfilesModel model=mapperProfile.mapeo(profilesDTO);
            profileRepository.save(model);
            logger.info("Perfil guardado exitosamente.");
        }
    }

    public void editProfile(Integer id,ProfilesDTO profilesDTO){
        ProfilesModel model=profileRepository.findById(id).orElseThrow(()->new NotFoundException("No se encuentra el perfil con el ID "+id));
        model.setName(profilesDTO.getName());
        model.setStatus(profilesDTO.getStatus());
        profileRepository.save(model);
    }

}
