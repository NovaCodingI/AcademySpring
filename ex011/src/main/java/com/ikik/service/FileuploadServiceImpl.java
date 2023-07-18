package com.ikik.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ikik.mapper.FileuploadMapper;
import com.ikik.vo.FileuploadVO;

@Service
public class FileuploadServiceImpl implements FileuploadService {

	@Autowired
	FileuploadMapper mapper;
	
	@Override
	public List<FileuploadVO> getList(int bno) {

		return mapper.getList(bno);
	}

	@Override
	public int insert(FileuploadVO vo) {

		return mapper.insert(vo);
	}

}
