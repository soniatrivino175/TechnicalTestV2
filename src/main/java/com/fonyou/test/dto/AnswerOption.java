package com.fonyou.test.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Data;
import lombok.ToString;

//@Data
@ToString
public class AnswerOption {

	private String enumeration;
	
	@NotNull(message = "Cannot be null or empty")
	private String answer;
	
	@Pattern(regexp = "^(true|false)$", message = "Should be a boolean value")
	private String correct;

	public String getEnumeration() {
		return enumeration;
	}

	public void setEnumeration(String enumeration) {
		this.enumeration = enumeration;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public boolean getCorrect() {
		return Boolean.valueOf(correct) ;
	}

	public void setCorrect(String correct) {
		this.correct = correct;
	}
}