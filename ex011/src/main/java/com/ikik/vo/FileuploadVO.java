package com.ikik.vo;

import lombok.Data;

@Data
public class FileuploadVO {
	
	private String uuid;
	private String uploadPath;
	private String fileName;
	private String fileType;
	private int bno;
	
	// 저장된 파일경로
	private String savePath;
	private String s_savePath;
	
	// 이미지가 저장되어있어 그럼 썸네일도 그냥 가지고 오고 싶거든요
	// 필드 2개 생성
//	uploadpath + uuid + "_" + fileName;
}
