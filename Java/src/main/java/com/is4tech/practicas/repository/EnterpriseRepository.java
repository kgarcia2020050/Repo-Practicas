package com.is4tech.practicas.repository;

import com.is4tech.practicas.models.EnterprisesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnterpriseRepository extends JpaRepository<EnterprisesModel,String> {
    public abstract EnterprisesModel findByName(String name);
}
