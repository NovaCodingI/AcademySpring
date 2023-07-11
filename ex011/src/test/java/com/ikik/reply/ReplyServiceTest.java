package com.ikik.reply;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ikik.mapper.ReplyMapper;
import com.ikik.service.ReplyService;
import com.ikik.vo.ReplyVO;

import lombok.extern.log4j.Log4j;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyServiceTest {
	
	@Autowired
	ReplyService service;
	
	@Test
	public void test() {
		assertNotNull(service);
		List<ReplyVO> list = service.getList(30, null);
		log.info("===================================");
		log.info("list : " + list);
	}
	
	
}
