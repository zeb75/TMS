package com.tms.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tms.model.TimeLog;

public interface TimeLogRepository extends JpaRepository<TimeLog,Integer> {
	//SELECT t FROM TimeLog t WHERE t.userId = :id AND t. BETWEEN 
//	@Query(value = "SELECT * FROM timelog where user_id = ?1", nativeQuery = true)
//	List<TimeLog> findByUserId(int userId);
    List<TimeLog> findByUserIdAndStartTimeBetween(Integer userId, LocalDateTime startTime, LocalDateTime stopTime);
}
