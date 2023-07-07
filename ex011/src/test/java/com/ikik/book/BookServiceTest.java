package com.ikik.book;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.Model;

import com.ikik.mapper.BookMapper;
import com.ikik.service.BookService;
import com.ikik.vo.BookVO;
import com.ikik.vo.Criteria;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BookServiceTest {
	
	@Autowired
	BookService bookService;
	
	@Test
	public void getList() {
		assertNotNull(bookService);
		
		List<BookVO> list = bookService.getList(new Criteria(), null);
		
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
		
	}
	
	@Test
	public void getOne() {
		BookVO book = bookService.getOne(274, null);
		log.info(book);
	}

}
