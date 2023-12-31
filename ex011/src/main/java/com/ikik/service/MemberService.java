package com.ikik.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.ikik.vo.MemberVO;

@Service
public interface MemberService {
	
	public MemberVO login(MemberVO memberVO);
	
	public int signUp(MemberVO memberVO);
	
	public int idCheck(MemberVO memberVO);

	public void naverLogin(HttpServletRequest request, Model model);
	
// public class MemberService {
	
	// @Autowired
	// MemberDao dao;
	
	/*
	public Member login(Member paramMember, Model model) {
		Member member = dao.login(paramMember);
		if(member == null) {
			model.addAttribute("message", "아이디/비밀번호를 확인해주세요");
		} else {
			model.addAttribute("message", member.getName() + "님 환영합니다.");
		}
		return member;
	}
	*/
}
