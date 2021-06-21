package com.project.professor.allocation.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import com.project.professor.allocation.entity.Course;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")
public class CourseRepositoryTest 
{
	@Autowired
	private CourseRepository courseRepository;
	
	@Test
	void findAll()
	{
		//act
		List<Course> courses = courseRepository.findAll();
		
		//print
		System.out.println(courses);
	}
	
	@Test
	void findById()
	{
		//Arrange
		Long id = 3L;
		
		//act
		Course course = courseRepository.findById(id).orElse(null);
		
		//print
		System.out.println(course);
	}
	
	@Test
	void findByNameContainingIgnoreCase()
	{
		//Arrange
		String partOfCourseName = "urs";
		
		//act
		List<Course> courses = courseRepository.findByNameContainingIgnoreCase(partOfCourseName);
		
		//print
		System.out.println(courses);
	}
}
