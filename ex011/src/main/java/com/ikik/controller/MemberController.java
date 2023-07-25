package com.ikik.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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
	
	@GetMapping("/login/naver")
	public void naverLogin() {
		
	}
	
	@GetMapping("/login/naver_callback")
	public String naverLogin_callback(HttpServletRequest request
									, Model model) {
		service.naverLogin(request, model);
		
		return "/login/naver";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
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
			session.setAttribute("memberVO", memberVO);
			session.setAttribute("userId", memberVO.getId());
			Map<String, Object> map = responseMapMessage(REST_SUCCESS, "로그인 되었습니다.");
			
			if(memberVO.getRole() != null
					&& memberVO.getRole().contains("ADMIN_ROLE")) {
				// 관리자 로그인 → 관리자 페이지로 이동
				map.put("url", "/admin");
			} else {
				map.put("url", "/board/list");
			}
			
			
			// return responseMap(1, "로그인");
			// return responseMapMessage(REST_SUCCESS, "로그인 되었습니다.");		// 리턴을 map으로 받아줍시다.
			return map;
		} else {
			// return responseMap(0, "로그인");
			return responseMapMessage(REST_FAIL, "아이디와 비밀번호를 확인해주세요.");
		}
	}
	
	@PostMapping("/idCheck")
	// 넘겨줄때도 JSON 문자열로 반환 할겁니다.
	public @ResponseBody Map<String, Object> idCheck(@RequestBody MemberVO memberVO){
		
		int res = service.idCheck(memberVO);
		
		// idCheck 와 같은 경우는 거꾸로 돌아간다
		// count = 1 로그인실패,
		// insert, update, delete > 0  true
		
		// decode(count(*),0,1,0), 0 이면 메세지 나오도록 수정함
		if(res == 0) {
			return responseMapMessage( REST_SUCCESS , "사용가능한 아이디 입니다.");
		} else {
			return responseMapMessage( REST_FAIL , "이미 사용중인 아이디 입니다.");
		}
		
	}
	
	@PostMapping("/register")
	public @ResponseBody Map<String, Object> register(@RequestBody MemberVO memberVO){
		
		try {
			int res = service.signUp(memberVO);
			return responseWriteMap(res);
		} catch (Exception e) {
			e.printStackTrace();
			return responseMapMessage(REST_FAIL, "등록중 예외사항이 발생 하였습니다.");
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
