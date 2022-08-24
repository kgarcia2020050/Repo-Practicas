package com.is4tech.practicas.service.repository;

import com.is4tech.practicas.models.UsersModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<UsersModel,Integer> {
    public abstract List<UsersModel> findByStatus(Byte status);

    public abstract UsersModel findByName(String name);
}
