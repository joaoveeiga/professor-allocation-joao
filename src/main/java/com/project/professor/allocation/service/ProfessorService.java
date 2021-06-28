package com.project.professor.allocation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.professor.allocation.entity.Department;
import com.project.professor.allocation.entity.Professor;
import com.project.professor.allocation.repository.ProfessorRepository;


import lombok.Data;

@Data
@Service
public class ProfessorService 
{
	private final ProfessorRepository professorRepository;
	private final DepartmentService departmentService;
	
	public Professor findById(Long id)
	{
		return professorRepository.findById(id).orElse(null);
	}
	
	public List<Professor> findAll()
	{
		return professorRepository.findAll();
	}
	
	public List<Professor> findByName (String name)
	{
		return professorRepository.findByNameContainingIgnoreCase(name);
	}
	
	public Professor findByCpf (String cpf)
	{
		return professorRepository.findByCpf(cpf).orElse(null);
	}
	
	public List<Professor> findByDepartmentId (Long departmentId)
	{
		return professorRepository.findByDepartmentId(departmentId);
	}
	
	public List<Professor> findAll (String name)
	{
		if (name==null || name.equals(""))
		{
			 return this.findAll();
		}
		else
		{
			return this.findByName(name);
		}
	}
	
	public Professor createSave(Professor professor)
	{
		if(professor != null)
		{
			professor.setId(null);
			return save(professor);
		}
		return null;
	}
	
	public Professor updateSave(Professor professor)
	{
		if(professor != null && professorRepository.existsById(professor.getId()))
		{
			return save(professor);
		}
		return null;
	}
	
	private Professor save (Professor professor)
	{
		professor = professorRepository.save(professor);
		
		Department department = professor.getDepartment();
		department = departmentService.findById(department.getId());
		professor.setDepartment(department);
		
		return professor;
	}
	
	public void deleteById (Long id)
	{
		if (id != null && professorRepository.existsById(id))
		{
			professorRepository.deleteById(id);
		}
	}
	
	public void deleteAll()
	{
		professorRepository.deleteAllInBatch();
	}
}
