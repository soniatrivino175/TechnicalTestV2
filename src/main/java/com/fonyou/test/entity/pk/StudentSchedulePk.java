package com.fonyou.test.entity.pk;

import java.io.Serializable;

import javax.persistence.Id;

public class StudentSchedulePk implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private String examCode;

	@Id
	private String studentCode;
	
}
