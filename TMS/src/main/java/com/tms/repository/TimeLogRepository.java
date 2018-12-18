package com.tms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tms.model.TimeLog;

public interface TimeLogRepository extends JpaRepository<TimeLog,Integer> {

}
