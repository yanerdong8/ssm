package com.yed.common.service;

import java.util.List;

import com.yed.common.bean.User;

public interface UserService {
	public void save(User user);
	public List<User> list();
	int deleteByPrimaryKey(Long id);
}
