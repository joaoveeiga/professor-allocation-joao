package com.project.professor.allocation.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import com.project.professor.allocation.entity.Department;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")
public class DepartmentRepositoryTest 
{
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Test
	void findAll()
	{
		//act
		List<Department> departments = departmentRepository.findAll();
		
		//print
		System.out.println(departments);
	}
	
	@Test
	void findByNameContainingIgnoreCase ()
	{
		//Arrange
		String partOfTheDepartmentName = "E";
		
		//act
		List<Department> departments = departmentRepository.findByNameContainingIgnoreCase(partOfTheDepartmentName);
		
		//print
		System.out.println(departments);
	}
	
	@Test
	void findById()
	{
		//Arrange
		Long id = 3L;
		
		//act
		Department department = departmentRepository.findById(id).orElse(null);
		
		//print
		System.out.println(department);
	}
	
	@Test
	void save_create()
	{
		//Arrange
		Department department = new Department();
		department.setId(null);
		department.setName("Departamento A");
		
		//act
		department = departmentRepository.save(department);
		
		//print
		System.out.println(department);
	}
	
	@Test
	void save_update()
	{
		//Arrange
		Department department = new Department();
		department.setName("Qualiti");
		department.setId(4L);

		//act
		if (!departmentRepository.findById(department.getId()).orElse(null).equals(null)) 
		{
			departmentRepository.findById(department.getId()).orElse(null).setName(department.getName());
		}
	
		//print
		System.out.println(departmentRepository.findById(department.getId()).orElse(null).getName());
	}
}
