package com.yed.system.dao;

import com.yed.common.dao.BaseMapper;
import com.yed.system.model.User;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {

    int insertSelective(User record);

    int updateByPrimaryKeySelective(User record);

    List<User> selectUserByRoleName(String roleName);
}