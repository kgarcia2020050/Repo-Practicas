package com.is4tech.practicas.controller;

import com.is4tech.practicas.dto.EnterpriseDTO;
import com.is4tech.practicas.bo.Enterprises;
import com.is4tech.practicas.service.EntrerprisesService;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/enterprise")
@CrossOrigin("http://localhost:4200/")
public class EnterprisesController {

    private final EntrerprisesService enterpriseServices;


    public EnterprisesController(EntrerprisesService enterpriseServices) {
        this.enterpriseServices = enterpriseServices;
    }

    @PostMapping("/new")
    public void save(@RequestBody @Valid EnterpriseDTO enterprisesModel) {
        enterpriseServices.save(enterprisesModel);
    }

    @GetMapping("/all")
    public List<Enterprises> findAll() {
        return this.enterpriseServices.findAll();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable("id") Integer id) {
        enterpriseServices.deleteById(id);
    }

}
