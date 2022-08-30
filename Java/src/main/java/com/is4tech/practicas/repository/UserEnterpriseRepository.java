package com.is4tech.practicas.repository;

import com.is4tech.practicas.bo.UsersEnterprises;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserEnterpriseRepository extends JpaRepository<UsersEnterprises, Integer> {
    public abstract List<UsersEnterprises> findAllByUserId(Integer id);
}
