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
	
	@Test
	void create_save()
	{
		//Arrange
		Course course = new Course();
		course.setName("Recode");
		
		//act
		course = courseRepository.save(course);
		
		//print
		System.out.println(course);
		
	}
	
	@Test
	void update_save()
	{
		//Arrange
		Course course = new Course();
		course.setName("Recode V");
		course.setId(5L);

		//act
		if (!courseRepository.findById(course.getId()).orElse(null).equals(null)) 
		{
			courseRepository.findById(course.getId()).orElse(null).setName(course.getName());
		}
	
		//print
		System.out.println(courseRepository.findById(course.getId()).orElse(null).getName());
	}
	
	@Test
	void deleteById()
	{
		Long id = 2L;
		
		courseRepository.deleteById(id);
	}
	
	@Test
	void deleteAll()
	{
		courseRepository.deleteAllInBatch();
	}
}
