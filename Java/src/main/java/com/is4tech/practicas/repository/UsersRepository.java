package com.is4tech.practicas.repository;

import com.is4tech.practicas.bo.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<Users,Integer> {
    public abstract List<Users> findByStatus(Byte status);

    public abstract Users findByName(String name);
}
