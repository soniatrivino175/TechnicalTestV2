package com.fonyou.test.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Entity
@Table(name="QUESTIONS")
public class QuestionEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String examCode;
		
	private int numberQuestion;
	
	private String question;
	
	private int score;

	public QuestionEntity(String examCode,  int numberQuestion, String question, int score) {
		super();
		this.examCode = examCode;
		this.numberQuestion = numberQuestion;
		this.question = question;
		this.score = score;
	}
	
	public QuestionEntity() {
		super();
	}
}
