package com.ikik.book;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ikik.mapper.BookMapper;
import com.ikik.vo.BookVO;
import com.ikik.vo.Criteria;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BookMapperTest {
	
	@Autowired
	BookMapper bookMapper;
	
	@Test
	public void getList() {
		assertNotNull(bookMapper);
		
		Criteria cri = new Criteria();
		cri.setSearchField("title");
		cri.setSearchWord("힘");
		
		List<BookVO> list = bookMapper.getList(cri);
//		List<BookVO> list = bookMapper.getList(new Criteria());
		
		log.info(list);
		
		/*
		// 반복할수있는 메서드 같은겁니다. ( 람다식 )
		// list에서 하나씩 꺼내서 저장해주는겁니다. 객체가 가지고 있는 값들을 출력해주는것. ( 반복문 )
		list.forEach(book ->{
			log.info("bookVO======================");
			log.info(book.getNo());
			log.info(book.getTitle());
			log.info(book.getAuthor());
			log.info("bookVO======================");
		});
		*/
		
	}
	
	@Test
	public void getTotalCnt() {
		int res = bookMapper.getTotalCnt(new Criteria());
		System.out.println("=====총건수==== : " + res);
		log.info(res);
	}
	
	@Test
	public void getOne() {
		BookVO book = bookMapper.getOne(274);
		System.out.println(book);
	}
	
	

}
