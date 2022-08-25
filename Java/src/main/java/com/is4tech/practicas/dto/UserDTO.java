package com.is4tech.practicas.dto;


import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class UserDTO {

    @NotEmpty
    @Size(min = 10,max = 80)
    private String email;
    @NotEmpty
    @Size(min = 8,max = 40)
    private String name;
    private Byte status;
    private Integer profile;


}
