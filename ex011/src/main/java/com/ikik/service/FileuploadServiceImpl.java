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

	@Override
	public int delete(int bno, String uuid) {
		// 파일 삭제
		
		// 데이터 베이스에서 삭제
		
		return mapper.delete(bno, uuid);
	}

//	@Override
//	public int delete(FileuploadVO vo) {
//		// TODO Auto-generated method stub
//		return mapper.delete(vo);
//	}

}
