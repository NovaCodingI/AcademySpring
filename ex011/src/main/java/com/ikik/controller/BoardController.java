package com.ikik.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ikik.mapper.BoardMapper;
import com.ikik.service.BoardService;
import com.ikik.vo.BoardVO;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/board/*")
@Log4j
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
//	@GetMapping("/board/list") //RequestMapping을 이용하여..
	@GetMapping("list")
	public void getList(Model model) {
		List<BoardVO> list = boardService.getListXml();
		log.info("========================");
		log.info(list);
		log.info("========================");
//		model.addAttribute("list", boardService.getListXml());
		model.addAttribute("list", list);
		
	}
	
	@GetMapping("view")
	public void getOne(Model model, int bno) {
		log.info("================== bno" + bno);
		model.addAttribute("board", boardService.getOne(bno));
	}
	
	@GetMapping("write")
	public void write(Model model) {
		
		// return "/board/write"; // 요청하는 그대로 반환하는것 void, board에 있는 write 요청에 있는 board
		// return "write"; // WEB-INF/views/ + return + .jsp
	}
	
	@PostMapping("write")
	public String writeAction(BoardVO board, Model model) {
//		req.setCharacterEncoding("utf-8"); // web.xml에 필터가 알아서 처리합니다. // (HttpServletRequest req) 매개변수
		log.info("================== board" + board);
		model.addAttribute("board", boardService.insert(board));
		
		String msg = "등록되었습니다";
		
		model.addAttribute("msg", msg);
		
		return "redirect:/board/list";
	 
	    // return "/board/write"; // 요청하는 그대로 반환하는것 void, board에 있는 write 요청에 있는 board
	    // return "write"; // WEB-INF/views/ + return + .jsp
		
	}
	
	// test
}
