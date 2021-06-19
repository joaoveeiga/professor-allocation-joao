package com.project.professor.allocation.entity;

import java.util.Date;
import java.time.DayOfWeek;
import javax.persistence.*;
//import javax.persistence.Id;

@Entity
public class Allocation 
{
	@Id
	private Long id;
	private Professor professor;
	private Course course;
	private DayOfWeek dayOfWeek;
	private Date start;
	private Date end;
	
	public Allocation ()
	{
		super ();
	}
	
	public Allocation (Long id, Professor professor, Course course, DayOfWeek dayOfWeek, Date start, Date end)
	{
		super ();
		this.id = id;
		this.professor = professor;
		this.course = course;
		this.dayOfWeek = dayOfWeek;
		this.start = start;
		this.end = end;
	}
	
	public Professor getProfessor ()
	{
		return this.professor;
	}
	
	public void setProfessor (Professor professor)
	{
		this.professor = professor;
	}
	
	public Date getStart() {
		return start;
	}

	public void setStart (Date start) 
	{
		this.start = start;
	}

	public Date getEnd () 
	{
		return end;
	}

	public void setEnd (Date end) 
	{
		this.end = end;
	}

	public Course getCourse ()
	{
		return this.course;
	}
	
	public void setCourse(Course course) 
	{
		this.course = course;
	}
	
	public DayOfWeek getDayOfWeek ()
	{
		return this.dayOfWeek;
	}
	
	public void setDayOfWeek (DayOfWeek dayOfWeek)
	{
		this.dayOfWeek = dayOfWeek;
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
