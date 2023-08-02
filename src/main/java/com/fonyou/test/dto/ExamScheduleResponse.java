package com.fonyou.test.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ExamScheduleResponse {

	List<StudentSchedule> success = new ArrayList<>();
	
	List<StudentScheduleError> failure = new ArrayList<>();
}
