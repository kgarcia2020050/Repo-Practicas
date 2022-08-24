package com.is4tech.practicas.controller;

import com.is4tech.practicas.dto.EnterpriseDTO;
import com.is4tech.practicas.models.EnterprisesModel;
import com.is4tech.practicas.service.EntrerprisesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enterprise")
public class EnterprisesController {

    @Autowired
    private EntrerprisesService enterpriseServices;

    @PostMapping("/new")
    public void save(@RequestBody EnterpriseDTO enterprisesModel){
        enterpriseServices.save(enterprisesModel);
    }

    @GetMapping("/all")
    public List<EnterprisesModel> findAll(){
        return enterpriseServices.findAll();
    }

    @PutMapping("/edit/{id}")
    public void editEnterprise(@PathVariable("id")Integer id,@RequestBody EnterpriseDTO enterpriseDTO){
        enterpriseServices.editEnterprise(id,enterpriseDTO);
    }
}
