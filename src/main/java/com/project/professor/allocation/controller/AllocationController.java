package com.project.professor.allocation.controller;

import java.util.List;
import java.util.Date;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project.professor.allocation.entity.Allocation;
import com.project.professor.allocation.service.AllocationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@Api(tags = {"allocations"})
@RequiredArgsConstructor
@RestController
@RequestMapping (path = "/allocations", produces = MediaType.APPLICATION_JSON_VALUE)
public class AllocationController 
{
	private final AllocationService allocationService;
	
	@ApiOperation (value = "Read all allocations.")
	@ApiResponse (code = 200, message = "OK")
	@GetMapping
	@ResponseStatus (HttpStatus.OK)
	public ResponseEntity<List<Allocation>> findAll ()
	{
		return new ResponseEntity<>(allocationService.findAll(), HttpStatus.OK);
	}
	
	@ApiOperation (value = "Read an allocation by id.")
	@ApiResponses 
	({
		@ApiResponse (code = 200, message = "OK!"),
		@ApiResponse (code = 404, message = "Not found!")
	})
	@GetMapping (path = "/{allocation_id}")
	@ResponseStatus (HttpStatus.OK)
	public ResponseEntity<Allocation> findById (@PathVariable(name = "allocation_id") Long id)
	{
		Allocation allocation = allocationService.findById(id);
		
		if (allocation == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>(allocation, HttpStatus.OK);
	}
	
	@ApiOperation (value = "Read an allocation by start hour.")
	@ApiResponse (code = 200, message = "OK")
	@GetMapping (path = "/{startHour}")
	@ResponseStatus (HttpStatus.OK)
	public ResponseEntity<List<Allocation>> findByStartHour (@PathVariable (name = "startHour") Date startHour)
	{
		if (startHour == null)
			return new ResponseEntity<>(allocationService.findAll(), HttpStatus.OK);
		else
			return new ResponseEntity<>(allocationService.findByStartHour(startHour), HttpStatus.OK);
	}
	
	@ApiOperation (value = "Read an allocation by end hour.")
	@ApiResponse (code = 200, message = "OK")
	@GetMapping (path = "/{endHour}")
	@ResponseStatus (HttpStatus.OK)
	public ResponseEntity<List<Allocation>> findByEndHour (@PathVariable (name = "endHour") Date endHour)
	{
		if (endHour == null)
			return new ResponseEntity<>(allocationService.findAll(), HttpStatus.OK);
		else
			return new ResponseEntity<>(allocationService.findByStartHour(endHour), HttpStatus.OK);
	}
	
	@ApiOperation (value = "Create an allocation.")
	@ApiResponses 
	({
		@ApiResponse (code = 201, message = "Created!"),
		@ApiResponse (code = 400, message = "Bad request!")
	})
	@PostMapping (consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus (HttpStatus.CREATED)
	public ResponseEntity<Allocation> createSave (@RequestBody Allocation allocation)
	{
		allocation = allocationService.createSave(allocation);
		
		if (allocation == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<>(allocation,HttpStatus.CREATED);
	}
	
	@ApiOperation (value = "Update a pre-existing allocation.")
	@ApiResponses 
	({
		@ApiResponse (code = 200, message = "OK!"),
		@ApiResponse (code = 404, message = "Not found!")
	})
	@PutMapping (path = "/{allocation_id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus (HttpStatus.OK)
	public ResponseEntity<Allocation> updateSave (@PathVariable (name = "allocation_id") Long id, @RequestBody Allocation allocation)
	{
		allocation.setId(id);
		allocation = allocationService.updateSave(allocation);
		if (allocation == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>(allocation, HttpStatus.OK);
	}
	
	@ApiOperation (value = "Delete an allocation by id.")
	@ApiResponse (code = 204, message = "No content.")
	@DeleteMapping (path = "/{allocation_id}")
	@ResponseStatus (HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deleteById (@PathVariable (name = "allocation_id") Long id)
	{
		allocationService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation (value = "Delete all allocations.")
	@ApiResponse (code = 204, message = "No content.")
	@DeleteMapping
	@ResponseStatus (HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deleteAll ()
	{
		allocationService.deleteAll();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
