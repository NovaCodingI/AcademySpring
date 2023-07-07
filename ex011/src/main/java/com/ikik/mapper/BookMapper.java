package com.ikik.mapper;

import java.util.List;

import com.ikik.vo.BookVO;
import com.ikik.vo.Criteria;

public interface BookMapper {
	
	public List<BookVO> getList(Criteria cri);
	
	public int getTotalCnt(Criteria cri);
	
	public BookVO getOne(int no);
	

}
