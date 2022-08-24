package com.is4tech.practicas.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "profiles", schema = "practicas", catalog = "")
public class ProfilesModel {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false)
    private int id;
    @Basic
    @Column(name = "NAME", nullable = true, length = 40)
    private String name;
    @Basic
    @Column(name = "STATUS", nullable = true)
    private Byte status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProfilesModel)) return false;
        ProfilesModel that = (ProfilesModel) o;
        return getId() == that.getId() && Objects.equals(getName(), that.getName()) && Objects.equals(getStatus(), that.getStatus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getStatus());
    }
}
