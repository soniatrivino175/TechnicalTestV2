package com.fonyou.test.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fonyou.test.controller.handle.ErrorHandlingController;
import com.fonyou.test.dto.Exam;
import com.fonyou.test.dto.ExamSchedule;
import com.fonyou.test.dto.Question;
import com.fonyou.test.dto.Response;
import com.fonyou.test.dto.StudentAnswer;
import com.fonyou.test.service.ExamService;

@Validated
@RestController
@RequestMapping("/exams")
public class ExamController extends ErrorHandlingController {

	private final ExamService examService;
	
	@Autowired
	public ExamController(ExamService examService) {
		super();
		this.examService = examService;
	}

	@PostMapping
	public ResponseEntity<?> createExam(@RequestBody @Valid Exam exam) {
		try {
			if( examService.existsExam(exam.getExamCode()) ) {
				return new ResponseEntity<>( 
					new Response(HttpStatus.CONFLICT.value(), 
						HttpStatus.CONFLICT.getReasonPhrase(), 
						"Exam code already exists"), HttpStatus.CONFLICT);
				
			}else if( examService.sumScoreQuestion(exam.getQuestions()) != 100 ) {
				
				return new ResponseEntity<>( 
						new Response(HttpStatus.BAD_REQUEST.value(), 
							HttpStatus.BAD_REQUEST.getReasonPhrase(), 
							"The sum of the scores doesn't equal 100"), HttpStatus.BAD_REQUEST);
			}else if( !examService.hasAnswerOptionCorrectUnique(exam.getQuestions()) ) {
				
				return new ResponseEntity<>( 
						new Response(HttpStatus.BAD_REQUEST.value(), 
							HttpStatus.BAD_REQUEST.getReasonPhrase(), 
							"All questions should have only one correct answer"), HttpStatus.BAD_REQUEST);
			}else {
				return ResponseEntity
						.ok()
						.body( examService.createExam(exam) );
			}
		}catch(Exception e) {
			return ResponseEntity
					.internalServerError()
					.body( 	new Response(HttpStatus.INTERNAL_SERVER_ERROR.value(), 
								HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), 
								e.getMessage()) );
		}
	}
	
	@PostMapping(value = "/schedule/{examCode}")
	public ResponseEntity<?> scheduleExam(@RequestBody @Valid ExamSchedule examSchedule,
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
	public ResponseEntity<?> answerExam(@RequestBody @Valid StudentAnswer studentAnswer,
			@PathVariable(name="examCode") String examCode,
			@PathVariable(name="studentCode") String studentCode) {
		try {
			if( !examService.hasNumberQuestionsOK( examCode, studentAnswer ) ) {
				return new ResponseEntity<>( 
						new Response(HttpStatus.BAD_REQUEST.value(), 
							HttpStatus.BAD_REQUEST.getReasonPhrase(), 
							"Some numberQuestion is invalid"), HttpStatus.BAD_REQUEST);
				
			}else if( examService.numberQuestionsDuplicated( studentAnswer ) ){
				return new ResponseEntity<>( 
						new Response(HttpStatus.BAD_REQUEST.value(), 
							HttpStatus.BAD_REQUEST.getReasonPhrase(), 
							"NumberQuestion duplicated"), HttpStatus.BAD_REQUEST);
			}else {
				return ResponseEntity
						.ok()
						.body( examService.saveStudentsAnswers(examCode, studentCode, studentAnswer) );
			}
		}catch(Exception e) {
			return ResponseEntity
					.internalServerError()
					.body( 	new Response(HttpStatus.INTERNAL_SERVER_ERROR.value(), 
							HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), 
							e.getMessage()) );
		}
	}
}