package com.is4tech.practicas.bo;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users_enterprises", schema = "practicas", catalog = "")
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UsersEnterprises)) return false;
        UsersEnterprises that = (UsersEnterprises) o;
        return getId() == that.getId() && Objects.equals(getUserId(), that.getUserId()) && Objects.equals(getEnterpriseId(), that.getEnterpriseId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUserId(), getEnterpriseId());
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }
}