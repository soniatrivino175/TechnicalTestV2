package com.fonyou.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fonyou.test.dto.Exam;
import com.fonyou.test.dto.ExamSchedule;
import com.fonyou.test.dto.Response;
import com.fonyou.test.dto.StudentAnswer;
import com.fonyou.test.service.ExamService;

@RestController
@RequestMapping("/exams")
public class ExamController {

	private final ExamService examService;
	
	@Autowired
	public ExamController(ExamService examService) {
		super();
		this.examService = examService;
	}

	@PostMapping
	public ResponseEntity<?> createExam(@RequestBody Exam exam) {
		try {
			return ResponseEntity
					.ok()
					.body( examService.createExam(exam) );
		}catch(Exception e) {
			return ResponseEntity
					.internalServerError()
					.body( 	new Response(HttpStatus.INTERNAL_SERVER_ERROR.value(), 
								HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), 
								e.getMessage()) );
		}
	}
	
	@PostMapping(value = "/schedule/{examCode}")
	public ResponseEntity<?> scheduleExam(@RequestBody ExamSchedule examSchedule,
			@PathVariable(name="examCode") String examCode) {
		try {
			return ResponseEntity
					.ok()
					.body( examService.scheduleExam(examCode, examSchedule) );
		}catch(Exception e) {
			return ResponseEntity
					.internalServerError()
					.body( 	new Response(HttpStatus.INTERNAL_SERVER_ERROR.value(), 
							HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), 
							e.getMessage()) );
		}
	}
	
	@PostMapping(value = "/answer/{examCode}/{studentCode}")
	public ResponseEntity<?> answerExam(@RequestBody StudentAnswer studentAnswer,
			@PathVariable(name="examCode") String examCode,
			@PathVariable(name="studentCode") String studentCode) {
		try {
			return ResponseEntity
					.ok()
					.body( examService.saveStudentsAnswers(examCode, studentCode, studentAnswer) );
		}catch(Exception e) {
			return ResponseEntity
					.internalServerError()
					.body( 	new Response(HttpStatus.INTERNAL_SERVER_ERROR.value(), 
							HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), 
							e.getMessage()) );
		}
	}
}