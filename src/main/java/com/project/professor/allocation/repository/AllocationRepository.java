package com.project.professor.allocation.repository;

import com.project.professor.allocation.entity.Allocation;

import java.sql.Time;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AllocationRepository extends JpaRepository<Allocation, Long> 
{
	Optional<Allocation> findById(Long id);
	List<Allocation> findByDayOfWeek (DayOfWeek dayOfWeek);
	List<Allocation> findByStartHour (Time startHour);
	List<Allocation> findByEndHour (Time endHour);
	List<Allocation> findByProfessor_id (Long id);
	List<Allocation> findByCourse_id (Long id);
}