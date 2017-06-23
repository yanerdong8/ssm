package com.yed.common.service;
import com.yed.common.model.Page;

import java.util.List;


public interface BaseService<T> {

    List<T> selectAll();

    T selectByPrimaryKey(Integer id);

    int insert(T t);

    int updateByPrimaryKey(T t);

    int deleteByPrimaryKey(Integer[] id);

    Page<T> findTByPage(Page<T> page, T t);

    List<T> findTByT(T t);

    T findTByTOne(T t);

}
