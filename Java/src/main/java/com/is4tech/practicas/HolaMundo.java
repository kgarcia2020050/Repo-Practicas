package com.is4tech.practicas;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/practicas")
@CrossOrigin("*")
public class HolaMundo {

    @GetMapping("/saludo")
    public String holaMundo(){
        return "Hola mundo";
    }

}
