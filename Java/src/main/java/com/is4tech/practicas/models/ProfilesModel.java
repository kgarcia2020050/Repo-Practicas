package com.is4tech.practicas.models;

import javax.persistence.*;

@Entity
@Table(name = "profiles", schema = "practicas", catalog = "")
public class ProfilesModel {
    @Id
    @Column(name = "NAME", nullable = false, length = 40)
    private String name;
    @Basic
    @Column(name = "STATUS", nullable = true)
    private Byte status;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProfilesModel that = (ProfilesModel) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
