package com.fonyou.test.dto;

import java.util.List;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class StudentAnswer {

	private List<Answer> answers;
}
