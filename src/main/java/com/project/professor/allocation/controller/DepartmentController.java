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

import com.project.professor.allocation.entity.Department;
import com.project.professor.allocation.service.DepartmentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@Api(tags = {"departments"})
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/departments", produces = MediaType.APPLICATION_JSON_VALUE)
public class DepartmentController 
{
	private final DepartmentService departmentService;
	
	@ApiOperation (value = "Read a department by name.")
	@ApiResponse (code = 200, message = "OK")
	@GetMapping
	@ResponseStatus (HttpStatus.OK)
	public ResponseEntity<List<Department>> findAll (@RequestParam(name = "name", required = false) String name)
	{
		return new ResponseEntity<>(departmentService.findAll(name), HttpStatus.OK);
	}
	
	@ApiOperation (value = "Read a department by id.")
	@ApiResponses 
	({
		@ApiResponse (code = 200, message = "OK!"),
		@ApiResponse (code = 404, message = "Not found!")
	})
	@GetMapping (path = "/{department_id}")
	@ResponseStatus (HttpStatus.OK)
	public ResponseEntity<Department> findById (@PathVariable(name = "department_id") Long id)
	{
		Department department = departmentService.findById(id);
		
		if (department == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>(department, HttpStatus.OK);
	}
	
	@ApiOperation (value = "Create a department.")
	@ApiResponses 
	({
		@ApiResponse (code = 201, message = "Created!"),
		@ApiResponse (code = 400, message = "Bad request!")
	})
	@PostMapping (consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus (HttpStatus.CREATED)
	public ResponseEntity<Department> createSave (@RequestBody Department department)
	{
		// Onde está o TRY para tratar o bad request?
		department = departmentService.createSave(department);

		if (department == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<>(department,HttpStatus.CREATED);
	}
	
	
	@ApiOperation (value = "Update a pre-existing department.")
	@ApiResponses 
	({
		@ApiResponse (code = 200, message = "OK!"),
		@ApiResponse (code = 404, message = "Not found!")
	})
	@PutMapping (path = "/{department_id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus (HttpStatus.OK)
	public ResponseEntity<Department> updateSave (@PathVariable (name = "department_id") Long id, @RequestBody Department department)
	{
		department.setId(id);
		// Onde está o TRY para tratar o bad request?
		department = departmentService.updateSave(department);
		if (department == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>(department, HttpStatus.OK);
	}
	
	@ApiOperation (value = "Delete a department by id.")
	@ApiResponse (code = 204, message = "No content.")
	@DeleteMapping (path = "/{department_id}")
	@ResponseStatus (HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deleteById (@PathVariable (name = "department_id") Long id)
	{
		departmentService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation (value = "Delete all departments.")
	@ApiResponse (code = 204, message = "No content.")
	@DeleteMapping
	@ResponseStatus (HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deleteAll ()
	{
		departmentService.deleteAll();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
