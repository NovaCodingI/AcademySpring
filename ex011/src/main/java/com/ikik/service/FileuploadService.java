package com.ikik.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ikik.vo.FileuploadVO;

@Service
public interface FileuploadService {

	List<FileuploadVO> getList(int bno);
	
	int insert(FileuploadVO vo);
	
//	int delete(FileuploadVO vo);
	
	int delete(int bno, String uuid);
	
	public int fileupload(List<MultipartFile> files, int bno) throws Exception;
}
