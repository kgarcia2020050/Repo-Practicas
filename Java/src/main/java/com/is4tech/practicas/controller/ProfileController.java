package com.is4tech.practicas.controller;

import com.is4tech.practicas.dto.ProfilesDTO;
import com.is4tech.practicas.models.ProfilesModel;
import com.is4tech.practicas.service.ProfilesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    private final ProfilesService profilesService;

    public ProfileController(ProfilesService profilesService) {
        this.profilesService = profilesService;
    }

    @PostMapping("/new")
    public void saveProfile(@RequestBody ProfilesDTO profilesDTO){
        profilesService.saveProfile(profilesDTO);
    }

    @GetMapping("/all")
    public List<ProfilesModel> findAll(){
        return profilesService.findAll();
    }

    @PutMapping("/editProfile/{id}")
    public void editProfile(@PathVariable("id")Integer id,@RequestBody ProfilesDTO profilesDTO){
        profilesService.editProfile(id,profilesDTO);
    }

}
