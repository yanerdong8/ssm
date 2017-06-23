package com.yed.common.service.impl;

import com.yed.common.dao.BaseMapper;
import com.yed.common.service.BaseService;
import com.yed.common.util.Reflections;
import com.yed.common.model.Page;
import com.yed.system.model.User;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Service
@Transactional
public abstract class BaseServiceImpl<T> implements BaseService<T> {
    @Autowired
    protected BaseMapper<T> baseMapper;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public List<T> selectAll() {
        return baseMapper.selectAll();

    }

    @Override
    public T selectByPrimaryKey(Integer id) {
        return baseMapper.selectByPrimaryKey(id);

    }

    @Override
    public int insert(T t) {
        insertAdditional(t);
        baseMapper.insert(t);
        Field field = Reflections.getAccessibleField(t, "id");
        Integer id = null;
        try {
            field.setAccessible(true);
            id = (Integer) field.get(t);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;

    }

    @Override
    public int updateByPrimaryKey(T t) {
        updateAdditional(t);
        return baseMapper.updateByPrimaryKey(t);

    }

    @Override
    public int deleteByPrimaryKey(Integer[] id) {
        return baseMapper.deleteByPrimaryKey(id);

    }

    @Override
    public Page<T> findTByPage(Page<T> page, T t) {
        page.setT(t);
        List<T> userList = baseMapper.findTByPage(page);
        page.setResult(userList);
        int count = baseMapper.findTCountByT(page.getT());
        page.setTotalCount(count);
        return page;

    }

    @Override
    public List<T> findTByT(T t) {
        return baseMapper.findTByT(t);

    }

    @Override
    public T findTByTOne(T t) {
        List<T> tOne = baseMapper.findTByT(t);
        if (tOne.size() > 0) {
            return tOne.get(0);
        } else {
            return null;
        }
    }

    /**
     * 反射赋值
     * @param t
     * @param fieldName
     * @param value
     * @throws IllegalAccessException
     */
    private void pushFieldValue(T t, String fieldName, Object value) throws IllegalAccessException {
        Field field = Reflections.getAccessibleField(t, fieldName);
        field.setAccessible(true);
        field.set(t, value);
    }

    protected void insertAdditional(T t){
        try {
            User user = (User) SecurityUtils.getSubject().getPrincipal();
            if(user != null){
                pushFieldValue(t, "createBy", user.getLoginName());
            }
            pushFieldValue(t, "createDate", sdf.format(new Date()));
            updateAdditional(t);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    protected void updateAdditional(T t) {
        try {
            User user = (User) SecurityUtils.getSubject().getPrincipal();
            if(user != null){
                pushFieldValue(t, "updateBy", user.getLoginName());
            }
            pushFieldValue(t, "updateDate", sdf.format(new Date()));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
