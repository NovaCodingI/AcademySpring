package com.ikik.member;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ikik.service.MemberService;
import com.ikik.vo.MemberVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class MemberServiceTest {
	
	@Autowired
	MemberService memberService;
	
	@Test
	public void test() {
		
		MemberVO member = new MemberVO();
		member.setId("admin");
		member.setPw("1234");
		
		member = memberService.login(member);
		
		log.info(member);
		assertNotNull(member);
	}
	
	@Test
	public void signUpTest() {
		
		MemberVO member = new MemberVO();
		member.setId("admin");
		member.setPw("1234");
		member.setName("admin");
		
		int res = memberService.signUp(member);
		
		log.info(member);
		assertNotNull(member);
		
		assertEquals(1, res);
		
	}
	
	@Test
	public void testIdCheck() {
		
		MemberVO member = new MemberVO();
		member.setId("heyhey");
		
		int res = memberService.idCheck(member);
		
		log.info(member);
		assertNotNull(member);
		
		assertEquals(1, res);
		
		System.out.println("결과" + res);
		
	}

}
