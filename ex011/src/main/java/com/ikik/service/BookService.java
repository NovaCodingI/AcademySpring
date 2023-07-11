package com.ikik.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.ikik.vo.BookVO;
import com.ikik.vo.Criteria;

@Service
public interface BookService {
	
	// 추상메서드 -> 미완성
	public List<BookVO> getList(Criteria cri, Model model);

	public int getTotalCnt(Criteria cri);
	
	public BookVO getOne(int no, Model model);
	
	

}
