package com.yed.common.dao;

import com.yed.common.model.Page;

import java.util.List;

public interface BaseMapper<T> {
    int deleteByPrimaryKey(Integer[] ids);
    
    int deleteByPrimaryKey(Integer ids);
    
    int insert(T t);

    T selectByPrimaryKey(Integer id);

    List<T> selectAll();

    int updateByPrimaryKey(T t);

	List<T> findTByPage(Page<T> page);

	int findTCountByT(T t);

	List<T> findTByT(T t);
	
	int deleteByT (T[] Ts);
}