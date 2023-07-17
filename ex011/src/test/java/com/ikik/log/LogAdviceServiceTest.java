package com.ikik.log;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ikik.service.LogService;
import com.ikik.vo.LogVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class LogAdviceServiceTest {
	
	@Autowired
	LogService logService;
	
	@Test
	public void test() {

		LogVO vo = new LogVO();

		vo.setClassName("클래스");
		vo.setMethodName("메소드");
		vo.setErrmsg("에러메세지");
		vo.setParams("파라메터");
		int res = logService.insert(vo);
		
		assertEquals(res, 1);
		
		log.info("==================================");
		log.info("Service res : " + res);
	}
	
}
