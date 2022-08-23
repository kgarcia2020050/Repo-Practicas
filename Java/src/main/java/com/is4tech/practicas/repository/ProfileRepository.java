package com.is4tech.practicas.repository;

import com.is4tech.practicas.models.ProfilesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileRepository extends JpaRepository<ProfilesModel,String> {
    public abstract ProfilesModel findByName(String name);
    public abstract List<ProfilesModel> findByStatus(Byte status);
}
