package com.fonyou.test.dto;

import java.util.List;

import org.dozer.Mapping;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Exam {

	@Mapping("code")
	private String examCode;
	
	@Mapping("name")
	private String examName;
	
	private List<Question> questions;
}
