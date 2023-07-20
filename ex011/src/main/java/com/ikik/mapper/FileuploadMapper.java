package com.ikik.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ikik.vo.FileuploadVO;

public interface FileuploadMapper {
	
	// 하나의 게시물당 여러개의 파일이 저장, 하나의 게시물당 여러개의 댓글이 달리는것과 일치
	// 리스트를 조회해야하는데 받아야할 파라메터가 있는것
	/**
	 * 하나의 게시물에 대해 업로드된 파일 목록을 조회
	 * @param bno
	 * @return
	 */
	public List<FileuploadVO> getList(int bno);
	
	public int insert(FileuploadVO vo);
	
//	public int delete(FileuploadVO vo);
	public int delete(@Param("bno")int bno
						, @Param("uuid") String uuid);

}
