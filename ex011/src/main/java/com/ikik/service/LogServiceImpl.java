package com.ikik.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ikik.mapper.LogMapper;
import com.ikik.vo.LogVO;

@Service
public class LogServiceImpl implements LogService {

	@Autowired
	LogMapper logMapper;
	
	@Override
	public int insert(LogVO vo) {
		return logMapper.intsert(vo);
	}

}
