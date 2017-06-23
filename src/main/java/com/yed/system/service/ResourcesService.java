package com.yed.system.service;

import com.yed.common.model.JsonTreeData;
import com.yed.common.service.BaseService;
import com.yed.system.model.Resources;

import java.util.List;

public interface ResourcesService extends BaseService<Resources>{

    int delete(Integer[] id);

    /**
     * 查询所有菜单
     * @return
     */
    List<JsonTreeData> findResources();

    /**
     * 查询用户菜单
     * @param userId
     * @return
     */
    List<JsonTreeData> findResourcesByUser(Integer userId);

    /**
     * 查询父节点
     * @param id
     * @return
     */
    Resources findParent(Integer id);

}
