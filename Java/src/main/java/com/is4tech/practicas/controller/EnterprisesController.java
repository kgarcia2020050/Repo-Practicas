package com.is4tech.practicas.controller;

import com.is4tech.practicas.dto.EnterpriseDTO;
import com.is4tech.practicas.bo.Enterprises;
import com.is4tech.practicas.service.EntrerprisesService;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/enterprise")
public class EnterprisesController {

    private final EntrerprisesService enterpriseServices;

    private static final Logger log = LogManager.getLogger(EnterprisesController.class);

    public EnterprisesController(EntrerprisesService enterpriseServices) {
        this.enterpriseServices = enterpriseServices;
    }

        @PostMapping("/new")
        public void save(@RequestBody @Valid EnterpriseDTO enterprisesModel){

        if(enterpriseServices.findByName(enterprisesModel.getName())!=null){
            log.info("Ya existe una empresa con este nombre.");
        }else{
            enterpriseServices.save(enterprisesModel);

        }

    }

    @GetMapping("/all")
    public Page<Enterprises> findAll(
            @RequestParam(defaultValue ="0")int page,
            @RequestParam(defaultValue = "10")int size,
            @RequestParam(defaultValue = "name")String order,
            @RequestParam(defaultValue = "true")boolean asc
                                     ){
        Page<Enterprises> enterprises=enterpriseServices.findAll(PageRequest.of(page,size, Sort.by(order)));
        if(!asc){
            enterprises=enterpriseServices.findAll(PageRequest.of(page,size, Sort.by(order).descending()));
        }
        return enterprises;
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<String> editEnterprise(@PathVariable("id")Integer id,@RequestBody EnterpriseDTO enterpriseDTO){

        enterpriseServices.editEnterprise(id,enterpriseDTO);
        return ResponseEntity.status(HttpStatus.OK).body("Empresa modificada exitosamente");
    }
}
