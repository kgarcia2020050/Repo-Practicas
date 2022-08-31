package com.is4tech.practicas.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsersEnterprisesDTO {
    private Integer id;
    private Integer userId;
    private Integer enterpriseId;
    private String enterpriseName;
}
