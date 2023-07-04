package com.ikik.board;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ikik.mapper.BoardMapper;
import com.ikik.vo.BoardVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardTest {
	
	@Autowired
	BoardMapper boardMapper;
	
	@Test
	public void getList() {
		assertNotNull(boardMapper);
		List<BoardVO> list = boardMapper.getList();
		
		// 반복할수있는 메서드 같은겁니다. ( 람다식 )
		// list에서 하나씩 꺼내서 저장해주는겁니다. 객체가 가지고 있는 값들을 출력해주는것. ( 반복문 )
		list.forEach(board ->{
			log.info("boardVO======================");
			log.info(board.getBno());
			log.info(board.getTitle());
			log.info(board.getContent());
		});
	}
	
	@Test
	public void getListXml() {
		
		List<BoardVO> list = boardMapper.getListXml();
		
		list.forEach(board ->{
			log.info("boardVOXML======================");
			log.info(board.getBno());
			log.info(board.getTitle());
			log.info(board.getContent());
		});
	}
	
	@Test
	public void insert() {
		BoardVO board = new BoardVO();
		board.setTitle("제목");
		board.setContent("내용");
		board.setWriter("글쓴이");
		int res = boardMapper.insert(board);
		assertEquals(res, 1);
		
	}
	@Test
	public void insertSelectKey() {
		BoardVO board = new BoardVO();
		board.setTitle("제목");
		board.setContent("내용");
		board.setWriter("글쓴이");
		
		int res = boardMapper.insertSelectKey(board);
		log.info("================================");
		log.info("bno : " + board.getBno());
		System.out.println("bno : " + board.getBno());
		assertEquals(res, 1);
	}
	
	@Test
	public void getOne() {
		BoardVO board = boardMapper.getOne(21);
		System.out.println("=======================");
		log.info(board);
		
	}
	
	@Test
	public void delete() {
		BoardVO board = new BoardVO();
		System.out.println("=======================");
		int res = boardMapper.delete(19);
		assertEquals(res, 1);
		log.info(board);
		
	}
	
	@Test
	public void update() {
		int bno = 11;
		
		BoardVO board = new BoardVO();
		System.out.println("=======================");
		log.info(board);
		board.setBno(bno);
		board.setTitle("제목 수정갑니다");
		board.setContent("내용 수정가요");
		board.setWriter("도착했나요");
		
		int res = boardMapper.update(board);
		
		System.out.println("======res======" + res);
		
		BoardVO getBoard = boardMapper.getOne(bno);
		
		assertEquals("제목 수정갑니다", getBoard.getTitle());
		assertEquals("내용 수정가요", getBoard.getContent());
		assertEquals("user03", getBoard.getWriter());
		assertEquals(res, 1);
		
	}
	
	@Test
	public void getTotalCnt() {
		int res = boardMapper.getTotalCnt();
		
		log.info("totalCnt : " + res);
		
	}
	
	
	
	

}
