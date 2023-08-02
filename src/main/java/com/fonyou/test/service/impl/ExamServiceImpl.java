package com.fonyou.test.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fonyou.test.dto.Answer;
import com.fonyou.test.dto.AnswerOption;
import com.fonyou.test.dto.Exam;
import com.fonyou.test.dto.ExamSchedule;
import com.fonyou.test.dto.ExamScheduleResponse;
import com.fonyou.test.dto.Question;
import com.fonyou.test.dto.StudentAnswer;
import com.fonyou.test.dto.StudentSchedule;
import com.fonyou.test.dto.StudentScheduleError;
import com.fonyou.test.entity.AnswerOptionEntity;
import com.fonyou.test.entity.ExamEntity;
import com.fonyou.test.entity.QuestionEntity;
import com.fonyou.test.entity.StudentAnswerEntity;
import com.fonyou.test.entity.StudentEntity;
import com.fonyou.test.entity.StudentScheduleEntity;
import com.fonyou.test.entity.projection.VWExamScoreStudent;
import com.fonyou.test.repository.AnswerOptionRepository;
import com.fonyou.test.repository.ExamRepository;
import com.fonyou.test.repository.QuestionRepository;
import com.fonyou.test.repository.StudentAnswerRepository;
import com.fonyou.test.repository.StudentRepository;
import com.fonyou.test.repository.StudentScheduleRepository;
import com.fonyou.test.service.ExamService;

@Service
public class ExamServiceImpl implements ExamService {

	private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

	private final Mapper mapper;
	
	private final StudentRepository studentRepository;
	
	private final ExamRepository examRepository;
	
	private final StudentScheduleRepository studentScheduleRepository;
	
	private final AnswerOptionRepository answerOptionRepository;
	
	private final QuestionRepository questionRepository;
	
	private final StudentAnswerRepository studentAnswerRepository;
	
	
	@Autowired
	public ExamServiceImpl(Mapper mapper, StudentRepository studentRepository, ExamRepository examRepository,
			StudentScheduleRepository studentScheduleRepository, AnswerOptionRepository answerOptionRepository,
			QuestionRepository questionRepository, StudentAnswerRepository studentAnswerRepository) {
		super();
		this.mapper = mapper;
		this.studentRepository = studentRepository;
		this.examRepository = examRepository;
		this.studentScheduleRepository = studentScheduleRepository;
		this.answerOptionRepository = answerOptionRepository;
		this.questionRepository = questionRepository;
		this.studentAnswerRepository = studentAnswerRepository;
	}

	@Override
	public Exam createExam(Exam exam) {
		
		int enumerator = 65;
		int numberQuestion = 1;
		examRepository.save( mapper.map(exam, ExamEntity.class) );		
		for( Question qe : exam.getQuestions() ) {
			enumerator = 65;
			qe.setNumberQuestion(numberQuestion++);
			Long questionId = saveQuestion( exam.getExamCode(), qe );
			for( AnswerOption ansO : qe.getAnswersOptions() ) {
				ansO.setEnumeration( String.valueOf((char)enumerator++) );
				saveAnswersOptions( questionId, ansO );				
			}
		}	
		return exam;
	}

	@Override
	public ExamScheduleResponse scheduleExam( String examCode, ExamSchedule examSchedule) {
		
		ExamScheduleResponse scheduleResponse = new ExamScheduleResponse();		
		for( String studentCode : examSchedule.getStudentCodes() ) {
			try {
				
				StudentScheduleEntity studentSchedule = saveStudentSchedule( examCode, studentCode, examSchedule.getExamDate() );
				if( studentSchedule != null ) {			
					String examDateStr = new SimpleDateFormat(DATE_FORMAT).format(studentSchedule.getExamDate());
					scheduleResponse.getSuccess().add(
						new StudentSchedule( studentCode, examDateStr, studentSchedule.getTimeZone() )
					);
				}else {
					scheduleResponse.getFailure().add(
						new StudentScheduleError(studentCode, "Student doesn't exist")
					);
				}
			}catch(Exception e) {
				scheduleResponse.getFailure().add(
					new StudentScheduleError(studentCode, e.getMessage())
				);
			}
		}
		return scheduleResponse;
	}

	@Override
	public VWExamScoreStudent saveStudentsAnswers( String examCode, String studentCode, StudentAnswer studentAnswer) {
		
		for( Answer ans : studentAnswer.getAnswers() ) {	
			QuestionEntity questionEntity = questionRepository.findByExamCodeAndNumberQuestion(examCode, ans.getNumberQuestion());
			AnswerOptionEntity answerOptionEntity = answerOptionRepository.findByQuestionIdAndEnumeration(questionEntity.getId(), ans.getEnumerationAnswer().toUpperCase());
			studentAnswerRepository.save(
				new StudentAnswerEntity(studentCode, questionEntity.getId(), answerOptionEntity.getId())
			);
		}
		return studentAnswerRepository.findByExamCodeAndStudentCode(examCode, studentCode);
	}
	

	private Long saveQuestion( String examCode, Question question ) {		
		return questionRepository.save( new QuestionEntity(examCode, question.getNumberQuestion(), question.getQuestion(), question.getScore()) ).getId();
	}
	
	private void saveAnswersOptions( Long questionId, AnswerOption answerOption ) {
		answerOptionRepository.save(
			new AnswerOptionEntity(questionId, answerOption.getEnumeration(), answerOption.getAnswer(), answerOption.isCorrect())
		);
	}
	
	private StudentScheduleEntity saveStudentSchedule( String examCode, String studentCode, Date examDate ) {
		StudentEntity student = studentRepository.findByCode(studentCode);
		if( student != null ) {
			Date examDateTimeZone = getDateFormattedToTimeZone(student.getTimeZone(), examDate );
			return studentScheduleRepository.save(
				new StudentScheduleEntity(examCode, studentCode, examDateTimeZone, student.getTimeZone())
			);
		}
		return null;
	}
	
	private String getDateStrFormattedToTimeZone(  String timeZone, Date date ) {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		sdf.setTimeZone( TimeZone.getTimeZone(timeZone) );
		return sdf.format(date);
	}
	
	private Date getDateFormattedToTimeZone(  String timeZone, Date date ) {
		try {
			return new SimpleDateFormat(DATE_FORMAT)
				.parse( getDateStrFormattedToTimeZone(  timeZone, date ) );
		} catch (ParseException e) {
			return null;
		}
	}
}
