package com.ikik.vo;

import lombok.Data;

@Data
public class LogVO {

	private String className;
	private String methodName;
	private String params;
	private String errmsg;
	private String regdate;
	
}
