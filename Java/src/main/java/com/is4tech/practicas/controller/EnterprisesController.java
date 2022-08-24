package com.is4tech.practicas.controller;

import com.is4tech.practicas.dto.EnterpriseDTO;
import com.is4tech.practicas.service.EntrerprisesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EnterprisesController {

    @Autowired
    private EntrerprisesService enterpriseServices;

    @PostMapping("/new")
    public void save(@RequestBody EnterpriseDTO enterprisesModel){
        enterpriseServices.save(enterprisesModel);
    }
}
