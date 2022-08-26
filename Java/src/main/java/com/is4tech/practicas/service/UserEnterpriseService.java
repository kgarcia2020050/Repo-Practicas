package com.is4tech.practicas.service;

import com.is4tech.practicas.bo.UsersEnterprises;
import com.is4tech.practicas.dto.UsersEnterprisesDTO;
import com.is4tech.practicas.mapper.MapperUsersEnterprises;
import com.is4tech.practicas.repository.UserEnterpriseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserEnterpriseService {
    private final UserEnterpriseRepository userEnterpriseRepository;
    private final MapperUsersEnterprises mapperUsersEnterprises;

    public UserEnterpriseService(UserEnterpriseRepository userEnterpriseRepository, MapperUsersEnterprises mapperUsersEnterprises) {
        this.userEnterpriseRepository = userEnterpriseRepository;
        this.mapperUsersEnterprises = mapperUsersEnterprises;
    }

    public List<UsersEnterprises> findByUser(Integer id) {
        return userEnterpriseRepository.findByUserId(id);
    }

    public void save(UsersEnterprisesDTO usersEnterprisesDTO) {
        UsersEnterprises uE = mapperUsersEnterprises.mapeo(usersEnterprisesDTO);
        userEnterpriseRepository.save(uE);
    }

}

