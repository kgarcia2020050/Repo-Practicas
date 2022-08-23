package com.is4tech.practicas.dto;


import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class UserDTO {

    @NotNull
    private String email;
    @NotNull
    private String name;
    @NotNull
    private Byte status;
    @NotNull
    private String profile;
}
