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

import com.project.professor.allocation.entity.Professor;
import com.project.professor.allocation.service.ProfessorService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@Api(tags = {"professors"})
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/professors", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfessorController 
{
	private final ProfessorService professorService;
	
	@ApiOperation (value = "Read a professor by name.")
	@ApiResponse (code = 200, message = "OK")
	@GetMapping
	@ResponseStatus (HttpStatus.OK)
	public ResponseEntity<List<Professor>> findAll(@RequestParam(name = "name", required = false) String name)
	{
		return new ResponseEntity<>(professorService.findAll(name), HttpStatus.OK);
	}
	
	@ApiOperation (value = "Read a professor by id.")
	@ApiResponses 
	({
		@ApiResponse (code = 200, message = "OK!"),
		@ApiResponse (code = 404, message = "Not found!")
	})
	@GetMapping(path = "{/professor_id}")
	@ResponseStatus (HttpStatus.OK)
	public ResponseEntity<Professor> findById(@PathVariable(name = "professor_id") Long id)
	{
		Professor professor = professorService.findById(id);
		if (professor == null)
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		else
		{
			return new ResponseEntity<>(professor, HttpStatus.OK);
		}
	}
	
	@ApiOperation (value = "Create a professor.")
	@ApiResponses 
	({
		@ApiResponse (code = 201, message = "Created!"),
		@ApiResponse (code = 400, message = "Bad request!")
	})
	@PostMapping
	@ResponseStatus (HttpStatus.CREATED)
	public ResponseEntity<Professor> create (@RequestBody Professor professor)
	{
		try
		{
			return new ResponseEntity<Professor> (professorService.createSave(professor), HttpStatus.CREATED);
		}
		catch (Exception e)
		{
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@ApiOperation (value = "Update a pre-existing professor.")
	@ApiResponses 
	({
		@ApiResponse (code = 200, message = "OK!"),
		@ApiResponse (code = 404, message = "Not found!")
	})
	@PutMapping(path = "/{professor_id}")
	@ResponseStatus (HttpStatus.OK)
	public ResponseEntity<Professor> update (@PathVariable(name = "professor_id") Long id, @RequestBody Professor professor)
	{
		professor.setId(id);
		Professor newProfessor = professorService.updateSave(professor);
		try 
		{
			if (newProfessor == null)
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			else
				return new ResponseEntity<>(newProfessor, HttpStatus.OK);
		}
		catch (Exception e)
		{
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation (value = "Delete a professor by id.")
	@ApiResponse (code = 204, message = "No content.")
	@DeleteMapping(path = "/{professor_id}")
	@ResponseStatus (HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deleteById (@PathVariable(name = "professor_id") Long id)
	{
		professorService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation (value = "Delete all professors.")
	@ApiResponse (code = 204, message = "No content.")
	@DeleteMapping
	@ResponseStatus (HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deleteAll ()
	{
		professorService.deleteAll();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
		
}