package com.ikik.mapper;

import java.util.List;

import com.ikik.vo.ReplyVO;

public interface ReplyMapper {
	public List<ReplyVO> getList(int bno);
	
	public int insert(ReplyVO vo);
	
	public int update(ReplyVO vo);
	
	public int delete(int rno);
	
}
