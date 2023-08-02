package com.fonyou.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fonyou.test.entity.StudentEntity;

public interface StudentRepository extends JpaRepository<StudentEntity, String> {

	StudentEntity findByCode( String code );
}
