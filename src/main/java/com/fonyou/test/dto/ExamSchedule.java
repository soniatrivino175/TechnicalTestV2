package com.fonyou.test.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ExamSchedule {

	private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	private static final String TIME_ZONE = "America/Bogota";
		
	private List<String> studentCodes;
	
	@JsonFormat(pattern=DATE_FORMAT, timezone=TIME_ZONE)
	private Date examDate;
}
