package com.fonyou.test.dto;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ExamSchedule {

	private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	private static final String TIME_ZONE = "America/Bogota";
	
	@Valid	
	@Size( min = 1, message = "Should not be less than 1 element")
	private List<String> studentCodes;
	
	@Future(message= "Should be in the future")
	@JsonFormat(pattern=DATE_FORMAT, timezone=TIME_ZONE)
	private Date examDate;
}
