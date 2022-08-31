package com.is4tech.practicas.bo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "users_enterprises", schema = "practicas", catalog = "")
@Getter
@Setter
public class UsersEnterprises {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private int id;
    @Basic
    @Column(name = "USER_ID", nullable = true)
    private Integer userId;
    @Basic
    @Column(name = "ENTERPRISE_ID", nullable = true)
    private Integer enterpriseId;
    @Basic
    @Column(name = "USER_NAME", nullable = true, length = 40)
    private String userName;
    @Basic
    @Column(name = "ENTERPRISE_NAME", nullable = true, length = 40)
    private String enterpriseName;


}