package com.project.professor.allocation.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project.professor.allocation.entity.Course;
import com.project.professor.allocation.service.CourseService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@Api(tags = {"courses"})
@RequiredArgsConstructor
@RestController
@RequestMapping (path = "/courses", produces = MediaType.APPLICATION_JSON_VALUE)
public class CourseController 
{
	private final CourseService courseService;
	
	@ApiOperation (value = "Read a course by name.")
	@ApiResponse (code = 200, message = "OK")
	@GetMapping
	@ResponseStatus (HttpStatus.OK)
	public ResponseEntity<List<Course>> findAll (@RequestParam(name = "name", required = false) String name)
	{
		return new ResponseEntity<>(courseService.findAll(name), HttpStatus.OK);
	}
	
	@ApiOperation (value = "Read a course by id.")
	@ApiResponses 
	({
		@ApiResponse (code = 200, message = "OK!"),
		@ApiResponse (code = 404, message = "Not found!")
	})
	@GetMapping (path = "/{course_id}")
	@ResponseStatus (HttpStatus.OK)
	public ResponseEntity<Course> findById (@PathVariable(name = "course_id") Long id)
	{
		Course course = courseService.findById(id);
		
		if (course == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>(course, HttpStatus.OK);
	}
	
	@ApiOperation (value = "Create a course")
	@ApiResponses 
	({
		@ApiResponse (code = 201, message = "Created!"),
		@ApiResponse (code = 400, message = "Bad request!")
	})
	@PostMapping (consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus (HttpStatus.CREATED)
	public ResponseEntity<Course> createSave (@RequestBody Course course)
	{
		try
		{
			return new ResponseEntity<Course> (courseService.createSave(course), HttpStatus.CREATED);
		}
		catch (Exception e)
		{
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation (value = "Update a pre-existing course.")
	@ApiResponses 
	({
		@ApiResponse (code = 200, message = "OK!"),
		@ApiResponse (code = 404, message = "Not found!"),
		@ApiResponse (code = 500, message = "Bad request!")
	})
	@PutMapping(path = "/{course_id}")
	@ResponseStatus (HttpStatus.OK)
	public ResponseEntity<Course> updateSave (@PathVariable(name = "course_id") Long id, @RequestBody Course course)
	{
		try 
		{
			course.setId(id);
			Course newCourse = courseService.updateSave(course);
			if (newCourse == null)
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			else
				return new ResponseEntity<>(newCourse, HttpStatus.OK);
		}
		catch (Exception e)
		{
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation (value = "Delete a course by id.")
	@ApiResponse (code = 204, message = "No content.")
	@DeleteMapping (path = "/{course_id}")
	@ResponseStatus (HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deleteById (@PathVariable (name = "course_id") Long id)
	{
		courseService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation (value = "Delete all courses.")
	@ApiResponse (code = 204, message = "No content.")
	@DeleteMapping
	@ResponseStatus (HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deleteAll ()
	{
		courseService.deleteAll();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
