package com.project.professor.allocation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.professor.allocation.entity.Course;
import com.project.professor.allocation.repository.CourseRepository;

import lombok.Data;

@Data
@Service
public class CourseService 
{
	private final CourseRepository courseRepository;
	
	public Course findById (Long id)
	{
		return courseRepository.findById(id).orElse(null);
	}
	
	public List<Course> findAll ()
	{
		return courseRepository.findAll();
	}
	
	public List<Course> findByName (String name)
	{
		return courseRepository.findByNameContainingIgnoreCase(name);
	}
	
	public List<Course> findAll (String name)
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
	
	public Course createSave (Course course)
	{
		if (course != null) 
		{
			course.setId(null);
			return save(course);
		}
		return null;
	}
	
	public Course updateSave (Course course)
	{
		if (course != null && courseRepository.findById(course.getId()) != null)
		{
 			return save(course);
		}
		return null;
	}
	
	private Course save (Course course)
	{
		course = courseRepository.save(course);
		return course;
	}
	
	public void deleteById (Long id)
	{
		if (id != null && courseRepository.findById(id) != null)
		{
			courseRepository.deleteById(id);
		}
	}
	
	public void deleteAll()
	{
		courseRepository.deleteAllInBatch();
	}
}
