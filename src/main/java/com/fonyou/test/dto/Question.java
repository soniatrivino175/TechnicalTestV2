package com.fonyou.test.dto;

import java.util.List;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Question {

	private Integer numberQuestion;
	
	private String question;
	
	private int score;
	
	private List<AnswerOption> answersOptions;
}
