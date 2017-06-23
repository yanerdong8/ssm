package com.yed.system.dao;

import java.util.List;

import com.yed.common.dao.BaseMapper;
import com.yed.system.model.Role;
import com.yed.system.model.User;

public interface UserMapper extends BaseMapper<User>{
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
}