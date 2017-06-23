package com.yed.system.dao;

import com.yed.common.dao.BaseMapper;
import com.yed.system.model.Dict;
import com.yed.system.model.Log;

public interface LogMapper extends BaseMapper<Log>{
    int deleteByPrimaryKey(Integer id);

    int insert(Log record);

    int insertSelective(Log record);

    Log selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Log record);

    int updateByPrimaryKeyWithBLOBs(Log record);

    int updateByPrimaryKey(Log record);
}