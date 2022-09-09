package com.is4tech.practicas.controller;

import com.is4tech.practicas.dto.EnterpriseDTO;
import com.is4tech.practicas.bo.Enterprises;
import com.is4tech.practicas.service.EntrerprisesService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


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
    public Page<Enterprises> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "4") int size,
            @RequestParam(defaultValue = "name") String order,
            @RequestParam(defaultValue = "true") boolean asc
    ) {
        Page<Enterprises> enterprises = enterpriseServices.findAll(PageRequest.of(page, size, Sort.by(order)));
        if (!asc) {
            enterprises = enterpriseServices.findAll(PageRequest.of(page, size, Sort.by(order).descending()));
        }
        return enterprises;
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable("id") Integer id) {
        enterpriseServices.deleteById(id);
    }

}
