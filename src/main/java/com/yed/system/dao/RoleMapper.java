package com.yed.system.dao;

import com.yed.common.dao.BaseMapper;
import com.yed.system.model.Role;

public interface RoleMapper extends BaseMapper<Role>{
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
}