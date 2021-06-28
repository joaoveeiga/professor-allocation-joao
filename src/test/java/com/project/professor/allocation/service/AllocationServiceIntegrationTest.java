package com.project.professor.allocation.service;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.project.professor.allocation.entity.Allocation;
import com.project.professor.allocation.entity.Course;
import com.project.professor.allocation.entity.Professor;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class AllocationServiceIntegrationTest 
{
	
	
	@Autowired
	AllocationService allocationService;
	
	@Autowired
	ProfessorService professorService;
	
	@Autowired
	CourseService courseService;
	
	SimpleDateFormat sdf = new SimpleDateFormat ("HH:mm");
	
	@Test
	public void createSave() throws ParseException
	{			
		Professor matheus = professorService.findById(15L);
		Professor amirton = professorService.findById(16L);
		Professor felipe = professorService.findById(17L);
		Professor tiago = professorService.findById(18L);
		Professor endrigo = professorService.findById(20L);
		
		Course backend = courseService.findById(7L);
		Course logica = courseService.findById(8L);
		Course banco = courseService.findById(9L);
		Course prototipacao = courseService.findById(10L);
		Course poo = courseService.findById(11L);
		
		Allocation allocationBackend = new Allocation (null, DayOfWeek.MONDAY, sdf.parse("19:00"), sdf.parse("22:00"), tiago, backend);
		Allocation allocationLogica = new Allocation (null, DayOfWeek.TUESDAY, sdf.parse("19:00"), sdf.parse("22:00"), matheus, logica);
		Allocation allocationBanco = new Allocation (null, DayOfWeek.WEDNESDAY, sdf.parse("19:00"), sdf.parse("22:00"), endrigo, banco);
		Allocation allocationPrototipacao = new Allocation (null, DayOfWeek.THURSDAY, sdf.parse("19:00"), sdf.parse("22:00"), felipe, prototipacao);
		Allocation allocationPOO = new Allocation (null, DayOfWeek.FRIDAY, sdf.parse("19:00"), sdf.parse("22:00"), amirton, poo);
		
		allocationBackend = allocationService.createSave(allocationBackend);
		allocationLogica = allocationService.createSave(allocationLogica);
		allocationBanco = allocationService.createSave(allocationBanco);
		allocationPrototipacao = allocationService.createSave(allocationPrototipacao);
		allocationPOO = allocationService.createSave(allocationPOO);
		
		List<Allocation> allocations = new ArrayList<Allocation>();
		allocations.add(allocationBackend);
		allocations.add(allocationLogica);
		allocations.add(allocationBanco);
		allocations.add(allocationPrototipacao);
		allocations.add(allocationPOO);
		
		allocations.forEach(System.out::println);		
	}
	
	@Test
	public void findById ()
	{
		Long id = 23L;
		
		System.out.println(professorService.findById(id));
	}
	
	@Test
	public void findAll ()
	{
		List<Allocation> allocations = allocationService.findAll();
		
		allocations.forEach(System.out::println);
	}
	
	@Test
	public void findByProfessorId ()
	{
		Long professorId = 7L;
		
		List<Allocation> allocations = allocationService.findByProfessor_id(professorId);
		
		allocations.forEach(System.out::println);
	}
	
	@Test
	public void findByCourse_id ()
	{
		Long courseId = 10L;
		
		List<Allocation> allocations = allocationService.findByCourse_id(courseId);
		
		allocations.forEach(System.out::println);
	}
	
	@Test
	public void findByDayOfWeek ()
	{
		DayOfWeek dayOfWeek = DayOfWeek.MONDAY;
		
		List<Allocation> allocations = allocationService.findByDayOfWeek(dayOfWeek);
		
		allocations.forEach(System.out::println);
	}
	
	@Test
	public void findByStartHour () throws ParseException
	{
	}
	
	@Test
	public void findByEndHour () throws ParseException
	{
	}
	
}
