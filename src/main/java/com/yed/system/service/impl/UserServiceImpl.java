package com.yed.system.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yed.common.model.Page;
import com.yed.common.service.impl.BaseServiceImpl;
import com.yed.common.shiro.PasswordHash;
import com.yed.system.dao.UserMapper;
import com.yed.system.dao.UserRoleMapper;
import com.yed.system.model.Role;
import com.yed.system.model.User;
import com.yed.system.model.UserRole;
import com.yed.system.service.UserService;

@Service
@Transactional
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService{
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserRoleMapper userRoleMapper;
	@Autowired
	private PasswordHash passwordHash;
	
	
	@Override
	public int insert(User user, Integer[] roleId) {
		// TODO Auto-generated method stub
		user.setPassword(passwordHash.toHex(user.getPassword()));
		userMapper.insert(user);
		
		if (null != roleId) {
			for(int i = 0; i < roleId.length; i++){
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
		// TODO Auto-generated method stub
		if (StringUtils.isNotEmpty(user.getPassword())) {
			user.setPassword(passwordHash.toHex(user.getPassword()));
		}
		
		UserRole userRole = new UserRole();
		userRole.setUserId(user.getId());
		userRoleMapper.deleteByT(new UserRole[]{userRole});
		
		if (null != roleId) {
			for(int i = 0; i < roleId.length; i++){
				userRole.setUserId(user.getId());
				userRole.setRoleId(roleId[i]);
				userRoleMapper.insert(userRole);
			}
		}
		return userMapper.updateByPrimaryKey(user);
	}
	@Override
	public int delete(Integer[] id) {
		// TODO Auto-generated method stub
		UserRole[] userRoles = new UserRole[id.length];
		for(int i = 0; i < id.length; i++){
			UserRole userRole = new UserRole();
			userRole.setUserId(id[i]);
			userRoles[i] = userRole;
		}
		userRoleMapper.deleteByT(userRoles);
		userMapper.deleteByPrimaryKey(id);
		
		return 0;
	}
	@Override
	public List<UserRole> findUserRoleByUser(User user) {
		// TODO Auto-generated method stub
		UserRole userRole = new UserRole();
		userRole.setUserId(user.getId());
		
		return userRoleMapper.findTByT(userRole);
	}
}

