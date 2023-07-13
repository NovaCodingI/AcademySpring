package com.ikik.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ikik.service.MemberService;
import com.ikik.vo.MemberVO;

@Controller
public class MemberController extends CommonRestController{

	@Autowired
	MemberService service;
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	/**
	 * JSON 형식의 데이터를 주고 받고 싶어요
	 * 페이지를 갱신하지 않고 원하는 데이터만 요청
	 * @param memberVO
	 * @param model
	 * @param session
	 * @return
	 */
	// ResponseBody JSON 타입으로 붙여주고싶어서..
	@PostMapping("/loginAction")
	public @ResponseBody Map<String, Object> loginAction(@RequestBody MemberVO memberVO
														, Model model
														, HttpSession session) {
		System.out.println("id :" + memberVO.getId());
		System.out.println("pw :" + memberVO.getPw());
		
		memberVO = service.login(memberVO);
		
		if(memberVO != null) {
			session.setAttribute("member", memberVO);
			session.setAttribute("userId", memberVO.getId());
			
			return responseMap(1, "로그인");
		} else {
			return responseMap(0, "로그인");
		}
	}
	
	
	
	/**
	 * 로그인 페이지로 이동
	 * @return
	 */
	/* 이전방식 com.ikik.dao 및 MemberService 주석처리 참고
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@PostMapping("/LoginAction")
	public String loginAcion(Member member, Model model) {
		System.out.println("id :" + member.getId());
		System.out.println("pw :" + member.getPw());
		
		service.login(member, model);
//		model.addAttribute("message", member.getId() + "환영합니다.");
		
		return "main";
	}
	*/
}
