package com.yed.system.dao;

import com.yed.common.dao.BaseMapper;
import com.yed.system.model.Role;
import com.yed.system.model.RoleResources;

public interface RoleResourcesMapper extends BaseMapper<RoleResources>{
    int deleteByPrimaryKey(Integer id);

    int insert(RoleResources record);

    int insertSelective(RoleResources record);

    RoleResources selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RoleResources record);

    int updateByPrimaryKey(RoleResources record);
}