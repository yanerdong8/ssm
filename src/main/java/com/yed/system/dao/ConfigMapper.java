package com.yed.system.dao;

import com.yed.common.dao.BaseMapper;
import com.yed.system.model.Config;

public interface ConfigMapper extends BaseMapper<Config>{
    int deleteByPrimaryKey(Integer id);

    int insert(Config record);

    int insertSelective(Config record);

    Config selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Config record);

    int updateByPrimaryKey(Config record);
}