package com.yed.system.service;

import java.util.List;

import com.yed.common.service.BaseService;
import com.yed.system.model.Role;
import com.yed.system.model.RoleResources;

public interface RoleService extends BaseService<Role>{

    int insert(Role role, Integer[] resourcesId);
    
    int update(Role role, Integer[] resourcesId);
    
    int delete(Integer[] id);

	List<RoleResources> findRoleResourcesByRole(Role role);

}
