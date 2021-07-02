package com.project.professor.allocation.repository;

import com.project.professor.allocation.entity.Allocation;

import java.time.DayOfWeek;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AllocationRepository extends JpaRepository<Allocation, Long> 
{
	List<Allocation> findByDayOfWeek (DayOfWeek dayOfWeek);
	List<Allocation> findByStartHour (Date startHour);
	List<Allocation> findByEndHour (Date endHour);
	List<Allocation> findByProfessor_id (Long id);
	List<Allocation> findByCourse_id (Long id);
}