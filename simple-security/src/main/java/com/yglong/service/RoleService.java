package com.yglong.service;

import com.yglong.model.Role;
import com.yglong.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public void addRoles(List<Role> roles) {
        roleRepository.saveAll(roles);
    }
}
