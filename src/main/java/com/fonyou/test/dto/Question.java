package com.fonyou.test.dto;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Question {

	private Integer numberQuestion;
	
	@NotNull(message = "Cannot be null or empty")
	private String question;
	
	@Min(value = 1, message = "Should not be less than 1")
	@Max(value = 100, message = "Should not be greater than 100")
	private int score;
	
	@Valid
	@Size( min = 4, max = 4, message = "Should have 4 answer options")
	private List<AnswerOption> answersOptions;
}
