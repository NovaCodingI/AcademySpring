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
import com.ikik.vo.Criteria;
import com.ikik.vo.ReplyVO;

import lombok.extern.log4j.Log4j;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyMapperTest {
	
	@Autowired
	ReplyMapper mapper;
	
	@Test
	public void test() {
		assertNotNull(mapper);
		Criteria cri = new Criteria();
		cri.setAmount(5); // 순서가 중요합니다. 기존에 등록되어있던 10개가 출력이 됩니다.
		cri.setPageNo(1);
		List<ReplyVO> list = mapper.getList(30, cri);
		log.info("===================================");
		log.info("list : " + list);
	}
	
	@Test
	public void insertTest() {
		
		ReplyVO vo = new ReplyVO();
		
		vo.setBno(30);
		vo.setReply("댓글이 남아나질 않네");
		vo.setReplyer("댓글마스터");
		vo.setReplyDate("sysdate");
		int res = mapper.insert(vo);
		
		assertEquals(res, 1);
		
		log.info("==================================");
		log.info("res : " + res);
	}
	
	@Test
	public void deleteTest() {
		int rno = 15;
		int res = mapper.delete(rno);
		
		assertEquals(1, res);
	}
	
	@Test
	public void updateTest() {
		ReplyVO vo = new ReplyVO();
		
		vo.setRno(30);
		vo.setReply("댓글이 남아나질 않네");
		int res = mapper.update(vo);
		
		assertEquals(res, 1);
		
		log.info("==================================");
		log.info("res : " + res);
	}
	
	@Test
	public void totalCntTest() {
		int res = mapper.totalCnt(30);
		
		System.out.println(res);
	}
	
}
