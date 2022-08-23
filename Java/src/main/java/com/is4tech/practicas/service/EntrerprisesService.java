package com.is4tech.practicas.service;

import com.is4tech.practicas.repository.EnterpriseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntrerprisesService {

    @Autowired
    private EnterpriseRepository enterpriseRepository;

}
