package com.ikik.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

/**
 * Lombok 라이브러리
 * getter/setter, equals, toString등의 메서드를 자동 생성 해줍니다.
 * 
 * Data 어노테이션
 * IDE(이클립스,STS)에 설치 후 lombok라이브러리를 추가 후 사용 가능
 * IDE에 설치가 되어 있지 않으면 어노테이션을 추가 해도 메서드가 생성되지 않을 수 있습니다.
 * Outline View를 통해 메서드가 생성되었는지 확인해주세요!
 * @author user
 *
 */
@Data
public class Member {
	
	// private로 지정하지 않아도 오류는 나지 않습니다.
	// 그러나 직접 접근을 막기 위해 private 로 지정해주어야합니다.
	private String id;
	private String pw;
	private String name;
	private int age;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dueDate;

}
