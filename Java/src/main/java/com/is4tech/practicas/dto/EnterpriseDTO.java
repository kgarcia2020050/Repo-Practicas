package com.is4tech.practicas.dto;

import com.sun.istack.NotNull;

public class EnterpriseDTO {
    @NotNull
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
