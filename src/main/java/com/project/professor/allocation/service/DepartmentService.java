package com.project.professor.allocation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.professor.allocation.entity.Department;
import com.project.professor.allocation.repository.DepartmentRepository;

import lombok.Data;

@Data
@Service
public class DepartmentService 
{
	private final DepartmentRepository departmentRepository;
	
	public Department findById (Long id)
	{
		return departmentRepository.findById(id).orElse(null);
	}
	
	public List<Department> findAll ()
	{
		return departmentRepository.findAll();
	}
	
	public List<Department> findByName (String name)
	{
		return departmentRepository.findByNameContainingIgnoreCase(name);
	}
	
	public List<Department> findAll (String name)
	{
		if (name == null || name.equals(""))
		{
			 return this.findAll();
		}
		else
		{
			return this.findByName(name);
		}
	}
	
	public Department createSave (Department department)
	{
		if (department != null) 
		{
			department.setId(null);
			return save(department);
		}
		return null;
	}
	
	public Department updateSave (Department department)
	{
		if (department != null && departmentRepository.findById(department.getId()) != null)
		{
 			return save(department);
		}
		return null;
	}
	
	private Department save (Department department)
	{
		department = departmentRepository.save(department);
		return department;
	}
	
	public void deleteById (Long id)
	{
		if (id != null && departmentRepository.findById(id) != null)
		{
			departmentRepository.deleteById(id);
		}
	}
	
	public void deleteAll()
	{
		departmentRepository.deleteAllInBatch();
	}
}
