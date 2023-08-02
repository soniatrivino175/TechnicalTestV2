package com.fonyou.test.dto;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.dozer.Mapping;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Exam {

	@NotNull(message = "Cannot be null or empty")
	@Mapping("code")
	private String examCode;
	
	@NotNull(message = "Cannot be null or empty")
	@Mapping("name")
	private String examName;
	
	@Valid
	@Size( min = 1, message = "Should have at least 1 question")
	private List<Question> questions;
}
