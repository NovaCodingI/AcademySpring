package com.ikik.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.ikik.vo.BoardVO;
import com.ikik.vo.Criteria;

@Service
public interface BoardService {
	
	
	
	public List<BoardVO> getListXml(Criteria cri, Model model);
	
	public int insert(BoardVO board);
	
//	public int insertSelectKey(BoardVO board);
	public int insertSelectKey(BoardVO board, List<MultipartFile> files) throws Exception;
	
	public BoardVO getOne(int bno);
	
	public int delete(int bno);
	
	public int update(BoardVO board, List<MultipartFile> files) throws Exception;
	
	public int getTotalCnt(Criteria cri);
	
	public int updateReplyCnt(@Param("bno")int bno, @Param("amount")int amount);
	
}
