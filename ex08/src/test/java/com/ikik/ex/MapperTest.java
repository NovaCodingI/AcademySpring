package com.ikik.ex;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ikik.mapper.SampleMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class MapperTest {
	
	// Mapper를 Test 하기 위해 MapperTest 클래스 파일 생성
	// 보통 연결이 잘 안되냐면..
	@Autowired
	SampleMapper sampleMapper;
	
	@Test
	public void test() {
		// 생성된 것이 null이 아닌가를 첫번째로 테스트합니다.
		assertNotNull(sampleMapper);
		// 그 이후에 테스트를 진행합니다.
		System.out.println("getTime()");
		System.out.println(sampleMapper.getTime());
		System.out.println("getTime2()");
		System.out.println(sampleMapper.getTime2());
	}
}
