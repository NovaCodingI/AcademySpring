package com.ikik.mapper;

import java.util.List;

import com.ikik.vo.MemberVO;


public interface MemberMapper {

	public MemberVO login(MemberVO memberVO);
	
	public int signUp(MemberVO memberVO);
	
	public int idCheck(MemberVO memberVO);
	
	public List<String> getMemberRole(String id);
}
