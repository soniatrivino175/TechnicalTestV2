package com.fonyou.test.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Response {

	private int code;
	
	private String status;
	
	private String message;

	public Response(int code, String status) {
		super();
		this.code = code;
		this.status = status;
	}

	public Response(int code, String status, String message) {
		super();
		this.code = code;
		this.status = status;
		this.message = message;
	}
}
