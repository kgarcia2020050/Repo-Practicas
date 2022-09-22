package com.is4tech.practicas.repository;

import com.is4tech.practicas.bo.UsersEnterprises;
import com.is4tech.practicas.dto.UsersEnterprisesDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserEnterpriseRepository extends JpaRepository<UsersEnterprises, Integer> {
    List<UsersEnterprises> findAllByUserId(Integer id);

    @Query(value = "select new com.is4tech.practicas.dto.UsersEnterprisesDTO(" +
            "ue.id, " +
            "ue.userId, " +
            "ue.enterpriseId, " +
            "ue.enterpriseName " +
            ") from UsersEnterprises ue " +
            "join Enterprises e on e.id = ue.enterpriseId " +
            "where ue.userId = :id ")
    List<UsersEnterprisesDTO> findAllDtoByUserId(Integer id);



}
