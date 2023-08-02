package com.fonyou.test.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Student {

	private String code;
	
	private String name;
	
	private int age;
	
	private String city;
	
	private String timeZone;
	
}
