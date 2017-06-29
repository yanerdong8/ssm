package com.yed.system.service.impl;

import com.yed.common.service.impl.BaseServiceImpl;
import com.yed.common.shiro.PasswordHash;
import com.yed.system.dao.UserMapper;
import com.yed.system.dao.UserRoleMapper;
import com.yed.system.model.User;
import com.yed.system.model.UserRole;
import com.yed.system.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
	
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private PasswordHash passwordHash;

    @Override
    public List<UserRole> findUserRoleByUser(User user) {
        UserRole userRole = new UserRole();
        userRole.setUserId(user.getId());
        return userRoleMapper.findTByT(userRole);
    }

    @Override
    public int insert(User user, Integer[] roleId) {
        user.setPassword(passwordHash.toHex(user.getPassword()));

        userMapper.insert(user);

        if (roleId != null) {
            for (int i = 0; i < roleId.length; i++) {
                UserRole userRole = new UserRole();
                userRole.setUserId(user.getId());
                userRole.setRoleId(roleId[i]);
                userRoleMapper.insert(userRole);
            }
        }
        return user.getId();
    }

    @Override
    public int update(User user, Integer[] roleId) {
        if (StringUtils.isNotEmpty(user.getPassword())) {
            user.setPassword(passwordHash.toHex(user.getPassword()));
        }
        UserRole param = new UserRole();
        param.setUserId(user.getId());
        userRoleMapper.deleteByT(new UserRole[]{param});

        if (roleId != null) {
            for (int i = 0; i < roleId.length; i++) {
                UserRole userRole = new UserRole();
                userRole.setUserId(user.getId());
                userRole.setRoleId(roleId[i]);
                userRoleMapper.insert(userRole);
            }
        }
        return userMapper.updateByPrimaryKey(user);
    }

    @Override
    public int delete(Integer[] id) {
        UserRole[] u = new UserRole[id.length];
        for (int i = 0; i < id.length; i++) {
            UserRole user = new UserRole();
            user.setUserId(id[i]);
            u[i] = user;
        }
        userRoleMapper.deleteByT(u);
        userMapper.deleteByPrimaryKey(id);

        return 0;
    }

    @Override
    public User selectByPrimaryKey(Integer id) {
        User u = userMapper.selectByPrimaryKey(id);
        u.setPassword("");
        return u;
    }

    @Override
    public List<User> findUserByRoleName(String roleName) {
        if (null == roleName || "".equals(roleName.trim())) {
            return userMapper.selectAll();
        } else {
            return userMapper.selectUserByRoleName(roleName);
        }

    }

}
