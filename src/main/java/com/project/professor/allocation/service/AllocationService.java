package com.project.professor.allocation.service;

import java.sql.Time;
import java.time.DayOfWeek;
import java.util.List;

import org.springframework.stereotype.Service;

import com.project.professor.allocation.entity.Allocation;
import com.project.professor.allocation.entity.Course;
import com.project.professor.allocation.entity.Professor;
import com.project.professor.allocation.repository.AllocationRepository;

import lombok.Data;

@Data
@Service
public class AllocationService 
{
	private final AllocationRepository allocationRepository;
	private final CourseService courseService;
	private final ProfessorService professorService;
	
	
	public Allocation findById (Long id)
	{
		return allocationRepository.findById(id).orElse(null);
	}
	
	public List<Allocation> findAll ()
	{
		return allocationRepository.findAll();
	}
	
	public List<Allocation> findByCourse_id (Long courseId)
	{
		return allocationRepository.findByCourse_id(courseId);
	}
	
	public List<Allocation> findByDayOfWeek (DayOfWeek dayOfWeek)
	{
		return allocationRepository.findByDayOfWeek(dayOfWeek);	
	}
	
	public List<Allocation> findByEndHour (Time endHour)
	{
		return allocationRepository.findByEndHour(endHour);	
	}
	
	public List<Allocation> findByStartHour (Time startHour)
	{
		//aqui percebi que só era necessário findByHour...
		return allocationRepository.findByStartHour(startHour);	
	}
	
	public List<Allocation> findByProfessor_id (Long professorId)
	{
		return allocationRepository.findByProfessor_id(professorId);
	}
	
	public Allocation createSave(Allocation professor)
	{
		if(professor != null)
		{
			professor.setId(null);
			return save(professor);
		}
		return null;
	}
	
	public Allocation updateSave(Allocation allocation)
	{
		// Essa linha está errada: allocationRepository.findById(allocation.getId()) != null
		// Nunca que o resultado de findById vai ser null, por conta o Optional.
		// Usa allocationRepository.existsById(allocation.getId()) e replica nas outras classes
		if(allocation != null && allocationRepository.findById(allocation.getId()) != null)
		{
			return save(allocation);
		}
		return null;
	}
	
	private Allocation save (Allocation allocation)
	{
		allocation = allocationRepository.save(allocation);
		
		Professor professor = allocation.getProfessor();
		professor = professorService.findById(professor.getId());
		allocation.setProfessor(professor);
		
		Course course = allocation.getCourse();
		course = courseService.findById(course.getId());
		allocation.setCourse(course);
		
		return allocation;
	}
	
	public void deleteById (Long id)
	{
		// Mesmo comentário do existsById
		if (id != null && allocationRepository.findById(id) != null)
		{
			allocationRepository.deleteById(id);
		}
	}
	
	public void deleteAll()
	{
		allocationRepository.deleteAllInBatch();
	}		
}