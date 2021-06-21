package com.project.professor.allocation.repository;

import java.sql.Time;
import java.time.DayOfWeek;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import com.project.professor.allocation.entity.Allocation;
import com.project.professor.allocation.entity.Course;
import com.project.professor.allocation.entity.Professor;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")
public class AllocationRepositoryTest 
{
	@Autowired
	private AllocationRepository allocationRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private ProfessorRepository professorRepository;
	
	@Test
	void findAll()
	{
		//act
		List<Allocation> allocations = allocationRepository.findAll();
		
		//print
		System.out.println(allocations);
	}
	
	@Test
	void findById ()
	{
		//arrange
		Long id = 1L;
		
		//act
		Allocation allocation = allocationRepository.findById(id).orElse(null);
		
		//print
		System.out.println(allocation);
	}
	
	@Test
	void findByDayOfWeek() //esse teste não rodou com o nome da coluna, apenas com o nome da variável, pq??
	{
		//arrange
		DayOfWeek dayOfWeek = DayOfWeek.SATURDAY;
		
		//act
		List<Allocation> allocations = allocationRepository.findByDayOfWeek(dayOfWeek);
		
		//print
		System.out.println(allocations);
	}
	
	@Test
	void findByStartHour () //Aqui tb não roda por pelo nome da coluna. Seria pq start e day são palavras reservadas??
	{
		//arrange
		//Aqui fiquei em dúvida como seria o melhor arrange para startHour
		//Qual o melhor objeto a ser criado aqui??
		Time startHour = new Time (0, 0, 0);
		
		//act
		List<Allocation> allocations = allocationRepository.findByStartHour(startHour);
		
		//print
		System.out.println(allocations);
	}
	
	@Test
	void findByEndHour ()
	{
		//arrange
		//Aqui fiquei em dúvida como seria o melhor arrange para startHour
		//Qual o melhor objeto a ser criado aqui??
		Time endHour = new Time (0, 0, 10);
		
		//act
		List<Allocation> allocations = allocationRepository.findByEndHour(endHour);
		
		//print
		System.out.println(allocations);
	}
	
	@Test
	void findByProfessor_id() //esse teste rodou com o nome da coluna.
	{
		//arrange
		Long professor_id = 2L;
		
		//act
		List<Allocation> allocations = allocationRepository.findByProfessor_id(professor_id);
		
		//print
		System.out.println(allocations);
	}
	
	@Test
	void findByCourse_id() //esse teste tb rodou com o nome da coluna.
	{
		//arrange
		Long course_id = 3L;
		
		//act
		List<Allocation> allocations = allocationRepository.findByCourse_id(course_id);
		
		//print
		System.out.println(allocations);
	}
	
	@Test
	void create_save()
	{
		//arrange
		Allocation allocation = new Allocation();
		Professor professor = professorRepository.findByCpf("1").orElse(null);
		Course course = courseRepository.findById(3L).orElse(null);
		allocation.setCourse(course);
		allocation.setDayOfWeek(DayOfWeek.FRIDAY);
		allocation.setProfessor(professor);
		Date endHour = new Date (79200000L);
		allocation.setEndHour(endHour);
		Date startHour = new Date (68400000L);
		allocation.setStartHour(startHour);
		
		//act
		allocation = allocationRepository.save(allocation);
		
		//print
		System.out.println(allocation);
	}
	
	@Test
	void update_save()
	{
		//arrange
		Date endHour = new Date (79200000L);
		Date startHour = new Date (68400000L);
		Allocation allocation = new Allocation();
		allocation.setCourse(courseRepository.findById(5L).orElse(null));
		allocation.setDayOfWeek(DayOfWeek.MONDAY);
		allocation.setEndHour(endHour);
		allocation.setStartHour(startHour);
		allocation.setId(1L);
		allocation.setProfessor(professorRepository.findById(2L).orElse(null));
		
		//act
		if(!allocationRepository.findById(allocation.getId()).orElse(null).equals(null))
		{
			allocationRepository.findById(allocation.getId()).orElse(null).setCourse(allocation.getCourse());
			allocationRepository.findById(allocation.getId()).orElse(null).setDayOfWeek(allocation.getDayOfWeek());
			allocationRepository.findById(allocation.getId()).orElse(null).setEndHour(allocation.getEndHour());
			allocationRepository.findById(allocation.getId()).orElse(null).setStartHour(allocation.getStartHour());
			allocationRepository.findById(allocation.getId()).orElse(null).setProfessor(allocation.getProfessor());
		}
		
		//print
		System.out.println("Novo professor: " + allocationRepository.findById(1L).orElse(null).getProfessor().getName());
	}
}
