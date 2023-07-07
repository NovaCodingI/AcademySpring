package com.ikik.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.ikik.mapper.BookMapper;
import com.ikik.vo.BookVO;
import com.ikik.vo.Criteria;
import com.ikik.vo.PageDto;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class BookServiceImpl implements BookService {

	// Mapper를 호출해줄겁니다.
	
	
	@Autowired
	BookMapper bookMapper;
	
	@Override
	public List<BookVO> getList(Criteria cri, Model model) {
		/*
		 * 1. 리스트 조회
		 * 2. 총건수 조회
		 * 3. 페이지DTO 생성(페이지블럭 생성)
		 * 
		 */
		
		// 리스트를 조회하고 반환합니다.
		List<BookVO> list = bookMapper.getList(cri);
		int totalCnt = bookMapper.getTotalCnt(cri);
		
		// 페이지의 정보를 담고있는 cri와 게시물의 총건수 totalCnt가 필요합니다.
		// 게시물의 총 건수로 페이지 블럭을 생성
		PageDto pageDto = new PageDto(cri, totalCnt);
		
		model.addAttribute("list", list);
		// pageDto 에 total이 있어요
		
		// 총건수 조회
		model.addAttribute("totalCnt");
		model.addAttribute("pageDto", pageDto);
		
		log.info("pageDto : " + pageDto);
		
		return null;
		// 모델받아서 서비스에 다 처리해줄겁니다 ( 데이터 저장하는것들 model에..)
//		return bookMapper.getList(cri);
	}
	
	@Override
	public int getTotalCnt(Criteria cri) {

		return bookMapper.getTotalCnt(cri);
	}

	@Override
	public BookVO getOne(int no, Model model) {
		BookVO book = bookMapper.getOne(no);
		model.addAttribute("book", book);
		return book;
	}

}
