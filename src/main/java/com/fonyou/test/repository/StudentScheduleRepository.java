package com.fonyou.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fonyou.test.entity.StudentScheduleEntity;

public interface StudentScheduleRepository extends JpaRepository<StudentScheduleEntity, String> {

}
