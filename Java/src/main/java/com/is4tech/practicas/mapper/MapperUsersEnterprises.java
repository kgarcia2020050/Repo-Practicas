package com.is4tech.practicas.mapper;

import com.is4tech.practicas.bo.UsersEnterprises;
import com.is4tech.practicas.dto.UsersEnterprisesDTO;
import org.springframework.stereotype.Component;

@Component
public class MapperUsersEnterprises implements MapperDTO<UsersEnterprisesDTO, UsersEnterprises> {

    @Override
    public UsersEnterprises mapeo(UsersEnterprisesDTO objeto) {
        UsersEnterprises usersEnterprises=new UsersEnterprises();
        usersEnterprises.setUserId(objeto.getUserId());
        usersEnterprises.setEnterpriseId(objeto.getEnterpriseId());
        return usersEnterprises;
    }
}
