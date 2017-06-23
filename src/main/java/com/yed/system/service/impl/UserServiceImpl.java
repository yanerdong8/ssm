package com.yed.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yed.common.model.Page;
import com.yed.common.service.impl.BaseServiceImpl;
import com.yed.common.shiro.PasswordHash;
import com.yed.system.dao.UserMapper;
import com.yed.system.dao.UserRoleMapper;
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
	public int insert(User muser, Integer[] roleId) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int update(User muser, Integer[] roleId) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int delete(Integer[] id) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public List<UserRole> findUserRoleByUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<User> findUserByRoleName(String roleName) {
		// TODO Auto-generated method stub
		return null;
	}

}
