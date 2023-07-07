package com.ikik.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ikik.service.BookService;
import com.ikik.vo.BookVO;
import com.ikik.vo.Criteria;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/book/*")
@Log4j
public class BookController {
	
	@Autowired
	BookService bookService;
	
	@GetMapping("list")
	public void list(Criteria cri, Model model) {
		// pageNo type int -> '' 입력 시 오류 발생
		
		log.info("cri : " + cri);
		
	    // 리스트 조회
//		List<BookVO> list = bookService.getList(cri, model);
		
		bookService.getList(cri, model);
		
		// 화면에 전달
//		model.addAttribute("list", list);
		model.addAttribute("msg", "/book/list");
		// return "/book/list" 이 생략되었습니다. (void로 처리함으로 인해서)
		// -> WEB-INF/views/book/list.jsp 가 응답이 됩니다.
	}
	
	@GetMapping("view")
	public void getOne(BookVO book, Model model) {
		bookService.getOne(book.getNo(), model);
	}
	

}
