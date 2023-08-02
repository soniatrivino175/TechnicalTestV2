package com.fonyou.test.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AnswerOption {

	private String enumeration;
	
	private String answer;
	
	private boolean correct;
}