package com.fonyou.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fonyou.test.entity.QuestionEntity;

public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {
	
	QuestionEntity findByExamCodeAndNumberQuestion( String examCode, int numberQuestion );
	
	Long countByExamCode( String examCode );
}
