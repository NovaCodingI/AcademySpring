package com.ikik.vo;

import lombok.Data;

@Data
public class BoardVO {

	// 직접 접근하지 못하게 Bean으로 사용하려면 private로 설정해주는것이 좋다
	// setter, getter로만 접근 할수있도록
	private int bno;
	private String title;
	private String content;
	private String writer;
	private String regdate;
	private String updateDate;

	
}
