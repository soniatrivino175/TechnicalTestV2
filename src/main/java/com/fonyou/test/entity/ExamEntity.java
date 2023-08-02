package com.fonyou.test.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Entity
@Table(name="EXAMS")
public class ExamEntity {

	@Id	
	private String code;
	
	private String name;
	
	@CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss")
    private Date dateCreated;
    
	@UpdateTimestamp    
    @Temporal(TemporalType.TIMESTAMP)      
    @JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss")
    private Date dateUpdated;
}
