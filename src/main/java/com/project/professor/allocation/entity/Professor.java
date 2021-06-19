package com.project.professor.allocation.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Professor 
{
	@Id
	private Long id;
	private String name;
	private String cpf;
	private Department department;
	
	public Professor ()
	{
		super();
	}
	
	public Professor (Long id, String name, String cpfDoProfessor, Department department)
	{
		super();
		this.id = id;
		this.name = name;
		this.cpf = cpfDoProfessor;
		this.department = department;
	}
	
	public String getCpf ()
	{
		return this.cpf;
	}
	
	public void setCpf(String cpf)
	{
		this.cpf = cpf;
	}
	
	/*public void setCpf(long cpf)
	{
		String cpfString;
		cpfString = String.valueOf(cpf);
		while (cpfString.length() < 11)
		{
			cpfString = "0"+cpfString;
		}
		this.setCpf(cpfString);
	}*/
	
	public String getName ()
	{
		return this.name;
	}
	
	public void setName (String name)
	{
		this.name = name;
	}
	
	public Department getDepartment ()
	{
		return this.department;
	}
	
	public void setDepartment (Department department)
	{
		this.department = department;
	}
	
	public Long getId() 
	{
		return id;
	}

	public void setId(Long id) 
	{
		this.id = id;
	}
}
