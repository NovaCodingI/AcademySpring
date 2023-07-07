package com.ikik.vo;

import lombok.Data;

@Data
public class BookVO {
	
	private int no;		// 도서 일련번호
	private String title;	// 도서명
	private String author;	// 작가
	
	private String sfile;	// 저장된파일명
	private String ofile;	// 원본파일명

	private String id;		// 대여자 ID
	private String rentyn;	// 도서 대여여부
	
	private String rentynStr; // 데이터베이스 조회할때 넣어주면 되겠죠 추가로 생성
	
	private String rentno;	// 대여번호
	private String StartDate;	// 대여시작일
	private String endDate;		// 반납가능일
	private String returnDate;	// 반납일
	
	
	public BookVO() {
		
	}
	
	public BookVO(String title, String author) {
		this.title = title;
		this.author = author;
		// 신규도서이므로 N
		this.rentyn = "N";
	}
	
	public BookVO(int no, String title, String rentyn, String author) {
		this.no = no;
		this.title = title;
		this.rentyn = rentyn;
		this.author = author;
	}

	@Override
	public String toString() {
		String rentYNStr = "";
		
		// 도서가 rentYN=Y(대여중)인 경우 대여중으로 표시
		if("Y".equals(getRentyn())) {
			rentYNStr="대여중";
		}
		return getNo()
				+ " " + getTitle()
				+ " " + getAuthor()
				+ " " + rentYNStr;
	}
	
}
