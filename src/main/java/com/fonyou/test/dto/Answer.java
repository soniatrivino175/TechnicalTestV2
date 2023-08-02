package com.fonyou.test.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Answer {

	@Positive(message = "Should be greater than 0")
	private int numberQuestion;

	@NotNull(message = "Cannot be null or empty")
	@Pattern(regexp = "^[AaBbCcDc]$", message = "Should be one of these values: A,B,C,D")
	private String enumerationAnswer;
}