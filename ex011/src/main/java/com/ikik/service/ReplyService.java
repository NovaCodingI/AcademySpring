package com.ikik.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ikik.vo.ReplyVO;

@Service
public interface ReplyService {

	List<ReplyVO> getList(int bno);
	
	public int insert(ReplyVO vo);
	
	public int delete(int rno);
	
	public int update(ReplyVO vo);
}
