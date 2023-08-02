package com.fonyou.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fonyou.test.entity.ExamEntity;

public interface ExamRepository extends JpaRepository<ExamEntity, String> {

}
