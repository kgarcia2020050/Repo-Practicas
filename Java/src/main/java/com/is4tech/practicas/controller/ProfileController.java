package com.is4tech.practicas.controller;

import com.is4tech.practicas.dto.ProfilesDTO;
import com.is4tech.practicas.bo.Profiles;
import com.is4tech.practicas.service.ProfilesService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value = "/profile")
@CrossOrigin("http://localhost:4200/")
public class ProfileController {

    private final ProfilesService profilesService;


    public ProfileController(ProfilesService profilesService) {
        this.profilesService = profilesService;
    }

    @PostMapping("/new")
    public void saveProfile(@RequestBody @Valid ProfilesDTO profilesDTO) {
            profilesService.saveProfile(profilesDTO);
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

    @GetMapping("/findByStatus/{status}")
    public List<Profiles> findAllByStatus(@PathVariable("status")Byte status){
        return this.profilesService.findAllByStatus(status);
    }

    @PutMapping("/editProfile/{id}")
    public void editProfile(@PathVariable("id") Integer id, @RequestBody @Valid ProfilesDTO profilesDTO) {
            profilesService.verification(id, profilesDTO);
    }

}
