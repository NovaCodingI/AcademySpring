package com.ikik.member;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ikik.mapper.MemberMapper;
import com.ikik.vo.MemberVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class MemberTest {
	
	@Autowired
	MemberMapper memberMapper;
	
	@Test
	public void test() {
		
		MemberVO member = new MemberVO();
		member.setId("admin");
		member.setPw("1234");
		
		member = memberMapper.login(member);
		
		log.info(member);
		assertNotNull(member);
	}

}
