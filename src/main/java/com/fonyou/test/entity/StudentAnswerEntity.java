package com.fonyou.test.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fonyou.test.entity.pk.StudentAnswerPk;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Entity
@IdClass(StudentAnswerPk.class)
@Table(name="STUDENTS_ASWERS")
public class StudentAnswerEntity {
	
	@Id
	private String studentCode;
	
	@Id
	private Long questionId;
	
	private Long answerOptionId;
	
	@CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss")
    private Date dateCreated;
    
	@UpdateTimestamp	    
    @Temporal(TemporalType.TIMESTAMP)      
    @JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss")
    private Date dateUpdated;

	public StudentAnswerEntity(String studentCode, Long questionId, Long answerOptionId) {
		super();
		this.studentCode = studentCode;
		this.questionId = questionId;
		this.answerOptionId = answerOptionId;
	}
	
	public StudentAnswerEntity() {
		super();
	}
}
