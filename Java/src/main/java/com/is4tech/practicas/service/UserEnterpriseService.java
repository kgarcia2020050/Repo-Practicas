package com.is4tech.practicas.service;

import com.is4tech.practicas.bo.Enterprises;
import com.is4tech.practicas.bo.Users;
import com.is4tech.practicas.bo.UsersEnterprises;
import com.is4tech.practicas.repository.UserEnterpriseRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserEnterpriseService {
    private final UserEnterpriseRepository userEnterpriseRepository;

    public UserEnterpriseService(UserEnterpriseRepository userEnterpriseRepository) {
        this.userEnterpriseRepository = userEnterpriseRepository;
    }

    public List<UsersEnterprises> findByUserId(Integer id) {
        return userEnterpriseRepository.findAllByUserId(id);
    }

    public void save(Users users, List<Enterprises> empresas) {
        List<UsersEnterprises> misEmpresas = new ArrayList<>();

        for (int i = 0; i < empresas.size(); i++) {
            UsersEnterprises enterprises = new UsersEnterprises();
            enterprises.setUserId(users.getId());
            enterprises.setUserName(users.getName());
            enterprises.setEnterpriseId(empresas.get(i).getId());
            enterprises.setEnterpriseName(empresas.get(i).getName());
            misEmpresas.add(enterprises);
        }
        userEnterpriseRepository.saveAll(misEmpresas);
    }

    public void edit(Users users, List<Enterprises> empresas) {
/*            List<UsersEnterprises> enterprises = findByUserId(users.getId());
            for (int i = 0; i < empresas.size(); i++) {
                enterprises.get(i).setUserId(users.getId());
                enterprises.get(i).setEnterpriseId(empresas.get(i).getId());
                enterprises.get(i).setEnterpriseName(empresas.get(i).getName());
                enterprises.get(i).setUserName(users.getName());
            }
            userEnterpriseRepository.saveAll(enterprises);*/


    }

}