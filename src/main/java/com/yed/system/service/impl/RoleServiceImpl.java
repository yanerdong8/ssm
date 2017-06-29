package com.yed.system.service.impl;

import com.yed.common.service.impl.BaseServiceImpl;
import com.yed.system.dao.RoleMapper;
import com.yed.system.dao.RoleResourcesMapper;
import com.yed.system.dao.UserRoleMapper;
import com.yed.system.model.Role;
import com.yed.system.model.RoleResources;
import com.yed.system.model.UserRole;
import com.yed.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private RoleResourcesMapper roleResourcesMapper;

    @Override
    public List<Role> selectAll() {
        return roleMapper.selectAll();
    }

    @Override
    public int insert(Role role, Integer[] resourcesId) {
        insertAdditional(role);
        roleMapper.insert(role);
        if (resourcesId != null) {
            for (int i = 0; i < resourcesId.length; i++) {
                RoleResources roleResources = new RoleResources();
                roleResources.setResourceId(resourcesId[i]);
                roleResources.setRoleId(role.getId());
                roleResourcesMapper.insert(roleResources);
            }
        }
        return role.getId();
    }

    @Override
    public int update(Role role, Integer[] resourcesId) {
        RoleResources param = new RoleResources();
        param.setRoleId(role.getId());
        roleResourcesMapper.deleteByT(new RoleResources[]{param});
        if (resourcesId != null) {
            for (int i = 0; i < resourcesId.length; i++) {
                RoleResources roleResources = new RoleResources();
                roleResources.setRoleId(role.getId());
                roleResources.setResourceId(resourcesId[i]);
                roleResourcesMapper.insert(roleResources);
            }
        }
        updateAdditional(role);
        return roleMapper.updateByPrimaryKey(role);
    }

    @Override
    public int delete(Integer[] id) {
        RoleResources[] r = new RoleResources[id.length];
        UserRole[] u = new UserRole[id.length];
        for (int i = 0; i < id.length; i++) {
            RoleResources roleResources = new RoleResources();
            UserRole userRole = new UserRole();
            roleResources.setRoleId(id[i]);
            userRole.setRoleId(id[i]);
            r[i] = roleResources;
            u[i] = userRole;
        }

        userRoleMapper.deleteByT(u);
        roleResourcesMapper.deleteByT(r);
        return roleMapper.deleteByPrimaryKey(id);
    }


    @Override
    public List<RoleResources> findRoleResourcesByRole(Role role) {
        RoleResources roleResources = new RoleResources();
        roleResources.setRoleId(role.getId());
        return roleResourcesMapper.findTByT(roleResources);
    }

}
