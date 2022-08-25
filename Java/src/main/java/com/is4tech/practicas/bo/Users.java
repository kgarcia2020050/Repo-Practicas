package com.is4tech.practicas.bo;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users", schema = "practicas", catalog = "")
public class Users {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private int id;
    @Basic
    @Column(name = "EMAIL", nullable = true, length = 80)
    private String email;
    @Basic
    @Column(name = "NAME", nullable = true, length = 40)
    private String name;
    @Basic
    @Column(name = "STATUS", nullable = true)
    private Byte status;
    @Basic
    @Column(name = "PROFILE", nullable = true,insertable = false,updatable = false)
    private Integer profile;
    @ManyToOne
    @JoinColumn(name = "PROFILE", referencedColumnName = "ID")
    private Profiles profilesByProfile;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Integer getProfile() {
        return profile;
    }

    public void setProfile(Integer profile) {
        this.profile = profile;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Users)) return false;
        Users that = (Users) o;
        return getId() == that.getId() && Objects.equals(getEmail(), that.getEmail()) && Objects.equals(getName(), that.getName()) && Objects.equals(getStatus(), that.getStatus()) && Objects.equals(getProfile(), that.getProfile()) && Objects.equals(getProfilesByProfile(), that.getProfilesByProfile());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEmail(), getName(), getStatus(), getProfile(), getProfilesByProfile());
    }

    public Profiles getProfilesByProfile() {
        return profilesByProfile;
    }

    public void setProfilesByProfile(Profiles profilesByProfile) {
        this.profilesByProfile = profilesByProfile;
    }
}
