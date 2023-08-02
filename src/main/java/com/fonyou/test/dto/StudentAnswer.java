package com.fonyou.test.dto;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class StudentAnswer {

	@Valid
	@Size( min = 4, max = 4, message = "Should have 4 answers")
	private List<Answer> answers;
}
