package com.is4tech.practicas.repository;

import com.is4tech.practicas.bo.Enterprises;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnterpriseRepository extends JpaRepository<Enterprises,Integer> {

    public abstract Enterprises findByName(String name);



}
