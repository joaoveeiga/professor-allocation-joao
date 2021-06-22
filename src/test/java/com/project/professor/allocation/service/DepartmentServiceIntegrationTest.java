package com.project.professor.allocation.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.project.professor.allocation.entity.Department;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class DepartmentServiceIntegrationTest
{
	@Autowired
	DepartmentService departmentService;
	
	@Test
	public void createSave ()
	{
		Department department = new Department(null, "Recode", null);
		
		department = departmentService.createSave(department);
	}
	
	@Test
	public void findById ()
	{
		Long id = 7L;
		
		System.out.println(departmentService.findById(id));
	}
	
	@Test
	public void findAll ()
	{
		List<Department> departments = departmentService.findAll();
		
		departments.forEach(System.out::println);
	}
	
	@Test
	public void findByName ()
	{
		String name = "a";
		
		List<Department> departments = departmentService.findByName(name);
		
		System.out.println("Departamentos os quais possuem a letra 'a' no nome: ");
		departments.forEach(System.out::println);
		
		name = "e";
		System.out.println();
		System.out.println("Departamentos os quais possuem a letra 'e' no nome: ");
		departments = departmentService.findByName(name);
		departments.forEach(System.out::println);
	}
	
	@Test
	public void findAll_()
	{
		String name = null;
		
		List<Department> departments = departmentService.findAll(name);
		
		departments.forEach(System.out::println);
	}
	
	@Test
	public void updateSave ()
	{
		Department department = departmentService.findById(7L);
		if (department != null)
		{
			department.setName("Recode V");
			
			department = departmentService.updateSave(department);
		}
		
	}
	
	@Test
	public void deleteById ()
	{
		Department department = departmentService.findById(7L);
		if (department != null)
			departmentService.deleteById(department.getId());
	}
	
	@Test
	public void deleteAll ()
	{
		departmentService.deleteAll();
	}
}
