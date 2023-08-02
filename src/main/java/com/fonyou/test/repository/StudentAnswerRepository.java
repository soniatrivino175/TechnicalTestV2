package com.fonyou.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fonyou.test.entity.StudentAnswerEntity;
import com.fonyou.test.entity.projection.VWExamScoreStudent;

public interface StudentAnswerRepository extends JpaRepository<StudentAnswerEntity, String> {

	@Query( value = "select ess.examCode, ess.examName, ess.studentCode, ess.studentName, sum(ess.scoreReceived) totalScoreReceived "
			+ "from vw_exam_score_students ess "
			+ "where ess.examCode = :examCode "
			+ "and ess.studentCode = :studentCode "
			+ "group by ess.examCode, ess.examName, ess.studentCode, ess.studentName", nativeQuery = true )
	VWExamScoreStudent findByExamCodeAndStudentCode( @Param("examCode") String examCode, @Param("studentCode") String studentCode );
}
