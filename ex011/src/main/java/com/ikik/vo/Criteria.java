package com.ikik.vo;

import lombok.Data;

// 클래스 안에서 세터 겟터 우클릭 해야 됩니다
@Data
public class Criteria {
	
	private String searchField; // 검색조건
	private String searchWord; // 검색어

	private int pageNo = 1;	// 요청한 페이지 번호
	private int amount = 10; // 한페이지당 보여질 게시물 수
	
	// DB에서 설정해도 되나 VO에서 설정하는 편이 쉬울수있다
	private int startNo = 1;
	private int endNo = 10;
	
	
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
		if(pageNo>0) {
			endNo = pageNo * amount;
			startNo = pageNo * amount - (amount-1);
		}
	}
	
	
}
