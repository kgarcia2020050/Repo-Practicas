package com.is4tech.practicas.controller;

import com.is4tech.practicas.dto.ProfilesDTO;
import com.is4tech.practicas.bo.Profiles;
import com.is4tech.practicas.service.ProfilesService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping(value = "/profile")
@CrossOrigin("http://localhost:4200/")
public class ProfileController {

    private final ProfilesService profilesService;


    public ProfileController(ProfilesService profilesService) {
        this.profilesService = profilesService;
    }

    @PostMapping("/new")
    public ResponseEntity<String> saveProfile(@RequestBody @Valid ProfilesDTO profilesDTO) {
        if (profilesService.findByName(profilesDTO.getName()) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ya existe un perfil con el mismo nombre.");
        } else {
            profilesService.saveProfile(profilesDTO);
            return null;

        }
    }

    @GetMapping("/profile/{id}")
    public Profiles getById(@PathVariable("id") Integer id) {
        return profilesService.findById(id);
    }

    @GetMapping("/all")
    public Page<Profiles> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size,
            @RequestParam(defaultValue = "name") String order,
            @RequestParam(defaultValue = "true") boolean asc

    ) {
        Page<Profiles> profiles = profilesService.findAll(PageRequest.of(page, size, Sort.by(order)));
        if (!asc) {
            profiles = profilesService.findAll(PageRequest.of(page, size, Sort.by(order).descending()));
        }
        return profiles;
    }

    @PutMapping("/editProfile/{id}")
    public ResponseEntity<String> editProfile(@PathVariable("id") Integer id, @RequestBody ProfilesDTO profilesDTO) {
        Profiles profile = profilesService.findById(id);
        Byte status = (profilesDTO.isStatus() ? (byte) 1 : (byte) 0);
        String name = profilesDTO.getName();
        if (name.equals(profile.getName()) && profile.getStatus().equals(status)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No has cambiado la informacion del perfil");
        } else if (name.equals(profile.getName())) {
            profilesService.editProfile(id, profilesDTO);
        } else if (profilesService.findByName(profilesDTO.getName()) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ya existe un perfil con el mismo nombre.");
        } else {
            profilesService.editProfile(id, profilesDTO);
        }
        return null;
    }

}
