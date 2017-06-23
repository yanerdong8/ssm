package com.yed.system.dao;

import com.yed.common.dao.BaseMapper;
import com.yed.system.model.Role;
import com.yed.system.model.UserRole;

public interface UserRoleMapper extends BaseMapper<UserRole>{
    int deleteByPrimaryKey(Integer id);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    UserRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);
}