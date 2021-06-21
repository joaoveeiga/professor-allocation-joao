package com.project.professor.allocation.repository;

import com.project.professor.allocation.entity.Department;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long>
{
	List<Department> findByNameContainingIgnoreCase(String partOfDepartmentName);
	Optional<Department> findById(Long id);
}