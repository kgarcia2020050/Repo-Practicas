package com.is4tech.practicas.repository;

import com.is4tech.practicas.bo.Profiles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileRepository extends JpaRepository<Profiles,Integer> {
    public abstract Profiles findByName(String name);
    public abstract List<Profiles> findByStatus(Byte status);
}
