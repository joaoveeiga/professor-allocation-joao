package com.project.professor.allocation.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Department 
{
	@Id
	private Long id;
	private String name;
	
	public Department ()
	{
		super ();
	}
	
	public Department (Long id, String name)
	{
		super ();
		this.id = id;
		this.name = name;
	}
	
	public String getName ()
	{
		return name;
	}
	
	public void setName (String name)
	{
		this.name = name;
	}

	public Long getId () 
	{
		return id;
	}

	public void setId (Long id) 
	{
		this.id = id;
	}
}
