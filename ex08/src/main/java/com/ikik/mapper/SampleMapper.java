package com.ikik.mapper;

import org.apache.ibatis.annotations.Select;

public interface SampleMapper {
	
	// 동적인 쿼리를 실행하기엔 많이 부족합니다.
	// 스프링의 가장큰 장점은 단위테스트, 객체를 생성하지않고 주입받을수있다!
	@Select("select sysdate from dual")
	String getTime();
	
	String getTime2();

}
