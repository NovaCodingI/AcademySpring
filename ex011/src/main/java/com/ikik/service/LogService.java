package com.ikik.service;

import org.springframework.stereotype.Service;

import com.ikik.vo.LogVO;

@Service
public interface LogService {

	int insert(LogVO vo);
	
}
