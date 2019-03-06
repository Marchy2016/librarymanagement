package com.roma.librarymanagment.services;

import com.roma.librarymanagment.model.Role;
import com.roma.librarymanagment.model.RoleName;
import com.roma.librarymanagment.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService  {

    private RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role add(RoleName name){
        return roleRepository.save(new Role(name));
    }
    public Role findRoleByName(String name){
        return roleRepository.findRoleByName(name);
    }
    public List<Role> findRoles(){
        return roleRepository.findAll();
    }


}
