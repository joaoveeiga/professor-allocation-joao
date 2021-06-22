package com.project.professor.allocation.repository;

import com.project.professor.allocation.entity.Professor;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long>
{
	List<Professor> findByNameContainingIgnoreCase(String partOfProfessorName);
	Optional<Professor> findByCpf(String cpf);
	List<Professor> findByDepartmentId (Long departmentId);
}