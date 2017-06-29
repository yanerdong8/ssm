package com.yed.system.service.impl;

import com.yed.system.service.LogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yed.common.service.impl.BaseServiceImpl;
import com.yed.system.model.Log;

@Service
@Transactional
public class LogServiceImpl extends BaseServiceImpl<Log> implements LogService {
	
}
