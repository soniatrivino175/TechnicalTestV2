package com.fonyou.test.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class StudentSchedule {

	private String studentCode;
	
	private String examDate;
	
	private String timeZone;
}
