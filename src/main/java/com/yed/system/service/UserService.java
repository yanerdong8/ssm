package com.yed.system.service;

import java.util.List;

import com.yed.common.service.BaseService;
import com.yed.system.model.User;
import com.yed.system.model.UserRole;

public interface UserService extends BaseService<User>{
	
	User selectByPrimaryKey(Integer id);
	
    int insert(User user, Integer[] roleId);
    
    int update(User user, Integer[] roleId);
    
    int delete(Integer[] id);
    
    List<UserRole> findUserRoleByUser(User user);
	
}
