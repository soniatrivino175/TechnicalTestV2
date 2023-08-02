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
	private List<Answer> answers;
}
