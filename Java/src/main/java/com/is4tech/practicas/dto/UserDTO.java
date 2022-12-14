package com.is4tech.practicas.dto;


import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class UserDTO {

    @NotEmpty(message = "El correo no puede estar vacio.")
    @Size(min = 10, max = 80, message = ("El correo debe medir entre 10 y 80 caracteres."))
    private String email;
    @NotEmpty(message = "El nombre no puede estar vacio.")
    @Size(min = 8, max = 40, message = "El nombre debe medir entre 8 y 40 caracteres")
    private String name;
    private boolean status;
    @NotNull(message = "Debes seleccionar un perfil.")
    private Integer profile;
    List<UsersEnterprisesDTO> empresas;


}