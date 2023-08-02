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
@Table(name="ASWERS_OPTIONS")
public class AnswerOptionEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private Long questionId;
	
	private String enumeration;
	
	private String answer;
	
	private boolean correct;

	public AnswerOptionEntity(Long questionId, String enumeration, String answer, boolean correct) {
		super();
		this.questionId = questionId;
		this.enumeration = enumeration;
		this.answer = answer;
		this.correct = correct;
	}
	
	public AnswerOptionEntity() {
		super();
	}
}
