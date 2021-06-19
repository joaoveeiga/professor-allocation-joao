package com.project.professor.allocation.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Course 
{
	@Id
	private Long id;
	private String name;
	
	
	public Course ()
	{
		super ();
	}
	public Course (Long id, String name)
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
