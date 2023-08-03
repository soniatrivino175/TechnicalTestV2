package com.fonyou.test.service;

import java.util.List;

import com.fonyou.test.dto.Exam;
import com.fonyou.test.dto.ExamSchedule;
import com.fonyou.test.dto.ExamScheduleResponse;
import com.fonyou.test.dto.Question;
import com.fonyou.test.dto.StudentAnswer;
import com.fonyou.test.entity.projection.VWExamScoreStudent;

public interface ExamService {

	Exam createExam( Exam exam );
	
	ExamScheduleResponse scheduleExam( String examCode, ExamSchedule examSchedule );
	
	VWExamScoreStudent saveStudentsAnswers( String examCode, String studentCode, StudentAnswer studentAnswer );
	
	boolean existsExam( String examCode );
	
	int sumScoreQuestion( List<Question> questions );
	
	boolean hasAnswerOptionCorrectUnique( List<Question> questions );
	
	boolean hasNumberQuestionsOK( String examCode, StudentAnswer studentAnswer );
	
	boolean numberQuestionsDuplicated( StudentAnswer studentAnswer );
}
