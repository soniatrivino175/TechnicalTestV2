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
import com.fonyou.test.entity.pk.StudentSchedulePk;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Entity
@IdClass(StudentSchedulePk.class)
@Table(name="STUDENT_SCHEDULE")
public class StudentScheduleEntity {

	public StudentScheduleEntity(String examCode, String studentCode, Date examDate, String timeZone) {
		super();
		this.examCode = examCode;
		this.studentCode = studentCode;
		this.examDate = examDate;
		this.timeZone = timeZone;
	}
	
	public StudentScheduleEntity() {
		super();
	}

	@Id
	private String examCode;

	@Id
	private String studentCode;
	
	private Date examDate;
	
	private String timeZone;
	
	@CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss")
    private Date dateCreated;
    
	@UpdateTimestamp	    
    @Temporal(TemporalType.TIMESTAMP)      
    @JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss")
    private Date dateUpdated;
}
