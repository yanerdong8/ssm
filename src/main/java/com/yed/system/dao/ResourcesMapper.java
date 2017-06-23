package com.yed.system.dao;

import com.yed.common.dao.BaseMapper;
import com.yed.system.model.Resources;

public interface ResourcesMapper extends BaseMapper<Resources>{
    int deleteByPrimaryKey(Integer id);

    int insert(Resources record);

    int insertSelective(Resources record);

    Resources selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Resources record);

    int updateByPrimaryKey(Resources record);
}