package com.fonyou.test.service.impl;

import java.util.List;
import java.util.TimeZone;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fonyou.test.dto.Question;
import com.fonyou.test.dto.Student;
import com.fonyou.test.entity.StudentEntity;
import com.fonyou.test.repository.StudentRepository;
import com.fonyou.test.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	private final Mapper mapper;
	
	private final StudentRepository studentRepository;
	
	@Autowired
	public StudentServiceImpl(Mapper mapper, StudentRepository studentRepository) {
		super();
		this.mapper = mapper;
		this.studentRepository = studentRepository;
	}

	@Override
	public void create(Student student) {
		StudentEntity studentEntity = mapper.map(student, StudentEntity.class);
		studentRepository.save(studentEntity);
	}
	
	@Override
	public boolean existsStudent( String studentCode ) {
		return studentRepository.findByCode(studentCode) != null;
	}
	
	@Override
	public boolean isValidTimeZone(String potentialTimeZone){
	    return TimeZone.getTimeZone(potentialTimeZone).getID().equals(potentialTimeZone);
	}
}
