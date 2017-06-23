package com.yed.system.service;

import java.util.List;

import com.yed.common.service.BaseService;
import com.yed.system.model.Dict;

public interface DictService extends BaseService<Dict>{
	public List<Dict> findDictByType(String dictType);
	public Dict findDictByTypeAndCode(String dictType,String dictCode);
}
