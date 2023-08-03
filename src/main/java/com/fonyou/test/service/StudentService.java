package com.fonyou.test.service;

import com.fonyou.test.dto.Student;

public interface StudentService {

	void create( Student student );
	
	boolean existsStudent( String studentCode );
	
	boolean isValidTimeZone(String potentialTimeZone);
}
