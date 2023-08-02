package com.fonyou.test.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fonyou.test.controller.handle.ErrorHandlingController;
import com.fonyou.test.dto.Response;
import com.fonyou.test.dto.Student;
import com.fonyou.test.service.StudentService;

@Validated
@RestController
@RequestMapping("/students")
public class StudentController extends ErrorHandlingController {

	private final StudentService studentService;
	
	@Autowired
	public StudentController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}

	@PostMapping
	public ResponseEntity<Response> createStudent(@RequestBody @Valid Student student) {
		try {
			studentService.create(student);
			return ResponseEntity
					.ok()
					.body( 	new Response(HttpStatus.OK.value(), 
								HttpStatus.OK.getReasonPhrase(), 
								"Student created") );
		}catch(Exception e) {
			return ResponseEntity
					.internalServerError()
					.body( 	new Response(
								HttpStatus.INTERNAL_SERVER_ERROR.value(), 
								HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), 
								e.getMessage()) );
		}
	}
}
