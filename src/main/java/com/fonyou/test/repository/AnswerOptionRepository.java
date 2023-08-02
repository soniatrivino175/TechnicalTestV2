package com.fonyou.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fonyou.test.entity.AnswerOptionEntity;

public interface AnswerOptionRepository extends JpaRepository<AnswerOptionEntity, Long> {

	AnswerOptionEntity findByQuestionIdAndEnumeration( Long questionId, String enumeration );
}
