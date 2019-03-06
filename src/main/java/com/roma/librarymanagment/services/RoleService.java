package com.roma.librarymanagment.services;

import com.roma.librarymanagment.model.Role;
import com.roma.librarymanagment.model.RoleName;

public interface RoleService {

    Role add(RoleName name);
    Role findRoleByName(String name);
}
