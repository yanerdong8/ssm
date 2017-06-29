package com.yed.system.service.impl;

import com.yed.common.model.Attributes;
import com.yed.common.model.JsonTreeData;
import com.yed.common.service.impl.BaseServiceImpl;
//import com.yed.common.TreeNodeUtil;

import com.yed.system.dao.ResourcesMapper;
import com.yed.system.dao.RoleResourcesMapper;
import com.yed.system.model.Resources;
import com.yed.system.model.RoleResources;
import com.yed.system.service.ResourcesService;
import com.yed.util.TreeNodeUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class ResourcesServiceImpl extends BaseServiceImpl<Resources> implements ResourcesService {
    @Autowired
    private ResourcesMapper resourcesMapper;
    @Autowired
    private RoleResourcesMapper roleResourcesMapper;

    @Override
    public int delete(Integer[] id) {
        int j = 0;
        for (int i = 0; i < id.length; i++) {
            if (!"1".equals(id[i])) {
                j++;
            }
        }
        RoleResources[] r = new RoleResources[j];
        for (int i = 0; i < id.length; i++) {
            if (!"1".equals(id[i])) {
                RoleResources roleResources = new RoleResources();
                roleResources.setResourceId(id[i]);
                r[i] = roleResources;
            }
        }
        if (j != 0) {
            roleResourcesMapper.deleteByT(r);
            resourcesMapper.deleteByPrimaryKey(id);
        }
        return 0;
    }

    @Override
    public int insert(Resources resources) {
        if ("".equals(resources.getrUrl())) {
            resources.setrUrl("/");
        }
        return resourcesMapper.insert(resources);
    }

    @Override
    public int updateByPrimaryKey(Resources resources) {
        if ("".equals(resources.getrUrl())) {
            resources.setrUrl("/");
        }
        return resourcesMapper.updateByPrimaryKey(resources);
    }

    @Override
    public List<JsonTreeData> findResources() {
        List<Resources> resourcesList = resourcesMapper.selectAll();
        return getJsonTreeData(resourcesList);
    }

    @Override
    public List<JsonTreeData> findResourcesByUser(Integer userId) {
        Resources resources = new Resources();
        resources.setId(userId);
        resources.setrType("1");
        List<Resources> resourcesList = resourcesMapper.findTByT(resources);
        return getJsonTreeData(resourcesList);
    }

    @Override
    public Resources findParent(Integer id) {
        Resources resources = resourcesMapper.selectByPrimaryKey(id);
        return resourcesMapper.selectByPrimaryKey(resources.getPid());
    }

    private List<JsonTreeData> getJsonTreeData(List<Resources> resourcesList) {
        List<JsonTreeData> treeDataList = new ArrayList<JsonTreeData>();
        for (Resources resource : resourcesList) {
            JsonTreeData treeData = new JsonTreeData();
            treeData.setId(resource.getId() == null ? null : resource.getId().toString());
            treeData.setPid(resource.getPid() == null ? null : resource.getPid().toString());
            treeData.setText(resource.getrName());
            Attributes attributes = new Attributes();
            attributes.setUrl(resource.getrUrl());
            attributes.setType(resource.getrType());
            treeData.setAttributes(attributes);
            treeDataList.add(treeData);
        }
        //最后得到结果集,经过FirstJSON转换后就可得所需的json格式
        return TreeNodeUtil.getfatherNode(treeDataList);
    }

}
