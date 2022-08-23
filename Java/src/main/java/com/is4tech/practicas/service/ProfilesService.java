package com.is4tech.practicas.service;

import com.is4tech.practicas.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfilesService {
    @Autowired
    private ProfileRepository profileRepository;
}
