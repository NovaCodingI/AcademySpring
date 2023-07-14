package com.ikik.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ikik.mapper.MemberMapper;
import com.ikik.vo.MemberVO;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	MemberMapper memberMapper;

	@Autowired
	BCryptPasswordEncoder encoder;
	
	
	@Override
	public MemberVO login(MemberVO paramMember) {
		
		// 사용자 정보 조회
		MemberVO memberVO = memberMapper.login(paramMember);
		
		if(memberVO != null) {
			// 사용자가 입력한 비밀번호가 일치하는지 확인
			// 사용자가 입력한 비밀번호, 데이터베이스에 암호화되어 저장된 비밀번호
			// 파라메터로 넘어온 paramMember
			boolean res = encoder.matches(paramMember.getPw(), memberVO.getPw());
			
			// 비밀번호 인증이 성공하면 member객체를 반환
			if(res) {
				return memberVO;
			} 
		}
		return null;
		//return memberMapper.login(memberVO);
	}

	@Override
	public int signUp(MemberVO memberVO) {
		
		// root-context.xml에 기입함.
//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		// 비밀번호 암호화
		// 입력된 비밀번호를 암호화 하고 다시 비밀번호에 넣어줌
		memberVO.setPw(encoder.encode(memberVO.getPw()));
		System.out.println("pw : " + memberVO.getPw());
		return memberMapper.signUp(memberVO);
	}

	@Override
	public int idCheck(MemberVO memberVO) {
		// TODO Auto-generated method stub
		return memberMapper.idCheck(memberVO);
	}

}
