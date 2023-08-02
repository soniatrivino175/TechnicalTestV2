package com.fonyou.test.service;

import com.fonyou.test.dto.Exam;
import com.fonyou.test.dto.ExamSchedule;
import com.fonyou.test.dto.ExamScheduleResponse;
import com.fonyou.test.dto.StudentAnswer;
import com.fonyou.test.entity.projection.VWExamScoreStudent;

public interface ExamService {

	Exam createExam( Exam exam );
	
	ExamScheduleResponse scheduleExam( String examCode, ExamSchedule examSchedule );
	
	VWExamScoreStudent saveStudentsAnswers( String examCode, String studentCode, StudentAnswer studentAnswer );
}
