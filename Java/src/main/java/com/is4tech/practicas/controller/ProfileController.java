package com.is4tech.practicas.controller;

import com.is4tech.practicas.dto.ProfilesDTO;
import com.is4tech.practicas.bo.Profiles;
import com.is4tech.practicas.service.ProfilesService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/profile")
@CrossOrigin("http://localhost:4200/")
public class ProfileController {

    private final ProfilesService profilesService;

    private static final Logger log = LogManager.getLogger(ProfileController.class);

    public ProfileController(ProfilesService profilesService) {
        this.profilesService = profilesService;
    }

    @PostMapping("/new")
    public void saveProfile(@RequestBody @Valid ProfilesDTO profilesDTO){
        if(profilesService.findByName(profilesDTO.getName())!=null){
            log.info("Ya existe un perfil con este nombre.");
        }else{
            profilesService.saveProfile(profilesDTO);
        }
    }

    @GetMapping("/all")
    public Page<Profiles> findAll(
            @RequestParam(defaultValue ="0")int page,
            @RequestParam(defaultValue = "10")int size,
            @RequestParam(defaultValue = "name")String order,
            @RequestParam(defaultValue = "true")boolean asc

    ){
        Page<Profiles> profiles=profilesService.findAll(PageRequest.of(page,size, Sort.by(order)));
        if(!asc){
            profiles=profilesService.findAll(PageRequest.of(page,size, Sort.by(order).descending()));
        }
        return profiles;
    }

    @PutMapping("/editProfile/{id}")
    public void editProfile(@PathVariable("id")Integer id,@RequestBody ProfilesDTO profilesDTO){
        profilesService.editProfile(id,profilesDTO);
    }

}
