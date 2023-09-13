package com.ikik.mapper;

import org.apache.ibatis.annotations.Select;

public interface SampleMapper {
	
	// 스프링의 가장큰 장점은 단위테스트, 객체를 생성하지않고 주입받을수있다!
	@Select("select sysdate from dual")
	String getTime();
	
	String getTime2();

}
