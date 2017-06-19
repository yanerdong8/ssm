package com.yed.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yed.common.bean.User;
import com.yed.common.dao.UserMapper;
import com.yed.common.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper userDao;
	
	public void setUserDao(UserMapper userDao) {
		this.userDao = userDao;
	}

	public UserMapper getUserDao() {
		return userDao;
	}
	
	@Override
	public List<User> list() {
		// TODO Auto-generated method stub
		return userDao.list();
	}

	@Override
	public void save(User user) {
		// TODO Auto-generated method stub
		userDao.save(user);
	}

	@Override
	public int deleteByPrimaryKey(Long id) {
		return userDao.deleteByPrimaryKey(id);
	}

}
