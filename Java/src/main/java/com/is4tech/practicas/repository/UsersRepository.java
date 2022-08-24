package com.is4tech.practicas.repository;

import com.is4tech.practicas.models.UsersModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<UsersModel,String> {
    public abstract List<UsersModel> findByStatus(Byte status);
}