package com.is4tech.practicas.models;

import javax.persistence.*;

@Entity
@Table(name = "enterprises", schema = "practicas", catalog = "")
public class EnterprisesModel {
    @Id
    @Column(name = "NAME", nullable = false, length = 40)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EnterprisesModel that = (EnterprisesModel) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
