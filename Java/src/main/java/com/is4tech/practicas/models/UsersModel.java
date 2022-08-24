package com.is4tech.practicas.models;

import javax.persistence.*;

@Entity
@Table(name = "users", schema = "practicas", catalog = "")
public class UsersModel {
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
    private ProfilesModel profilesByProfile;

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
        if (o == null || getClass() != o.getClass()) return false;

        UsersModel that = (UsersModel) o;

        if (id != that.id) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (profile != null ? !profile.equals(that.profile) : that.profile != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (profile != null ? profile.hashCode() : 0);
        return result;
    }

    public ProfilesModel getProfilesByProfile() {
        return profilesByProfile;
    }

    public void setProfilesByProfile(ProfilesModel profilesByProfile) {
        this.profilesByProfile = profilesByProfile;
    }
}
