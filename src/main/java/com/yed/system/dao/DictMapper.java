package com.yed.system.dao;

import com.yed.common.dao.BaseMapper;
import com.yed.system.model.Dict;

public interface DictMapper extends BaseMapper<Dict>{
    int deleteByPrimaryKey(Integer id);

    int insert(Dict record);

    int insertSelective(Dict record);

    Dict selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Dict record);

    int updateByPrimaryKey(Dict record);
}