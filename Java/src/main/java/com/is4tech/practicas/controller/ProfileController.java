package com.is4tech.practicas.controller;

import com.is4tech.practicas.dto.ProfilesDTO;
import com.is4tech.practicas.service.ProfilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private ProfilesService profilesService;


    @PostMapping("/new")
    public void saveProfile(@RequestBody ProfilesDTO profilesDTO){
        profilesService.saveProfile(profilesDTO);
    }

}
