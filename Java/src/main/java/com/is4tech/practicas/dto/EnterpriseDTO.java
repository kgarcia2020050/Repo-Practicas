package com.is4tech.practicas.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class EnterpriseDTO {
    @NotEmpty(message = "El nombre no puede estar vacio.")
    @Size(min = 8, max = 40, message = "El nombre debe medir entre 8 y 40 caracteres.")
    private String name;
    private boolean status;
    private boolean permission;
}
