package com.fonyou.test.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class StudentScheduleError {

	private String studentCode;
	
	private String error;
}
