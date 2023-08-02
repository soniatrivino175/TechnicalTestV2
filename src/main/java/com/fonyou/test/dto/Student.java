package com.fonyou.test.dto;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Student {

	@NotNull(message = "Cannot be null or empty")
	private String code;
	
	@NotNull(message = "Cannot be null or empty")
	private String name;
	
	@Min(value = 3, message = "Should not be less than 3")
	@Max(value = 150, message = "Should not be greater than 150")
	private int age;
	
	@NotNull(message = "Cannot be null or empty")
	private String city;
	
	@NotNull(message = "Cannot be null or empty")
	private String timeZone;
	
}
