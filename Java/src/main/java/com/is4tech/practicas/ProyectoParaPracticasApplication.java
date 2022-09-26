package com.is4tech.practicas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ProyectoParaPracticasApplication {

    public static final String NAME_EXPRESSION = "([a-zA-z]{1,50})(([\\s][a-zA-z]{1,50})?){50}$";

    public static final String EMAIL_EXPRESSION="^[\\w-]+(\\.[\\w-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,3})$";


    public static void main(String[] args) {

        SpringApplication.run(ProyectoParaPracticasApplication.class, args);
    }

}
