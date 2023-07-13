package com.ikik.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ikik.mapper.MemberMapper;
import com.ikik.vo.MemberVO;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	MemberMapper memberMapper;

	@Override
	public MemberVO login(MemberVO memberVO) {

		return memberMapper.login(memberVO);
	}

}
