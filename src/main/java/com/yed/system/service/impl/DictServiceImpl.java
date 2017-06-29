package com.yed.system.service.impl;


import com.yed.common.model.Page;
import com.yed.common.service.impl.BaseServiceImpl;
import com.yed.system.model.Dict;
import com.yed.system.service.DictService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("dictService")
@Transactional
public class DictServiceImpl extends BaseServiceImpl<Dict> implements DictService {

    @Cacheable({"dict-activeSessionCache"})
    @Override
    public List<Dict> selectAll() {
        return super.selectAll();
    }

    @Cacheable(value = "dict-activeSessionCache", key = "#id")
    @Override
    public Dict selectByPrimaryKey(Integer id) {
        return super.selectByPrimaryKey(id);

    }

    @CachePut(value = "dict-activeSessionCache", key = "#dict.getId()")
    @Override
    public int insert(Dict dict) {
        return super.insert(dict);

    }

    @CachePut(value = "dict-activeSessionCache", key = "#dict.getId()")
    @Override
    public int updateByPrimaryKey(Dict dict) {
        return super.updateByPrimaryKey(dict);

    }

    @CacheEvict(value = "dict-activeSessionCache", allEntries = true)
    @Override
    public int deleteByPrimaryKey(Integer[] ids) {
        return super.deleteByPrimaryKey(ids);

    }

    @Cacheable({"dict-activeSessionCache"})
    @Override
    public List<Dict> findTByT(Dict t) {
        return super.findTByT(t);

    }

    @Cacheable(value = "dict-activeSessionCache")
    @Override
    public Dict findTByTOne(Dict dict) {
        return super.findTByTOne(dict);
    }

    @Cacheable({"dict-activeSessionCache"})
    @Override
    public Page<Dict> findTByPage(Page<Dict> page, Dict t) {
        return super.findTByPage(page, t);
    }

    @Cacheable(value = "dict-activeSessionCache")
	@Override
	public List<Dict> findDictByType(String dictType) {
		Dict dict = new Dict();
		dict.setDictType(dictType);
		return super.findTByT(dict);
	}

    @Cacheable(value = "dict-activeSessionCache")
	@Override
	public Dict findDictByTypeAndCode(String dictType, String dictCode) {
		Dict dict = new Dict();
		dict.setDictType(dictType);
		dict.setDictCode(dictCode);
		return super.findTByTOne(dict);
	}

}
