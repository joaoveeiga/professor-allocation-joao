package com.project.professor.allocation.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.project.professor.allocation.entity.Course;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class CourseServiceIntegrationTest 
{
	@Autowired
	CourseService courseService;
	
	@Test
	public void createSave ()
	{
		Course backend = new Course(null, "Backend", null);
		Course logica = new Course(null, "Lógica", null);
		Course banco = new Course(null, "Banco de Dados", null);
		Course prototipacao = new Course(null, "Prototipação de Baixa Fidelidade", null);
		Course poo = new Course(null, "Programação Orientada a Objeto", null);
		
		backend = courseService.createSave(backend);
		logica = courseService.createSave(logica);
		banco = courseService.createSave(banco);
		prototipacao = courseService.createSave(prototipacao);
		poo = courseService.createSave(poo);
	}
	
	@Test
	public void findById ()
	{
		Long id = 7L;
		
		System.out.println(courseService.findById(id));
	}
	
	@Test
	public void findAll ()
	{
		List<Course> courses = courseService.findAll();
		
		courses.forEach(System.out::println);
	}
	
	@Test
	public void findByName ()
	{
		String name = "a";
		
		List<Course> courses = courseService.findByName(name);
		
		System.out.println("Departamentos os quais possuem a letra 'a' no nome: ");
		courses.forEach(System.out::println);
		
		name = "e";
		System.out.println();
		System.out.println("Departamentos os quais possuem a letra 'e' no nome: ");
		courses = courseService.findByName(name);
		courses.forEach(System.out::println);
	}
	
	@Test
	public void findAll_()
	{
		String name = null;
		
		List<Course> courses = courseService.findAll(name);
		
		courses.forEach(System.out::println);
	}
	
	@Test
	public void updateSave ()
	{
		Course course = courseService.findById(7L);
		if (course != null)
		{
			course.setName("Recode V");
			
			course = courseService.updateSave(course);
		}
		
	}
	
	@Test
	public void deleteById ()
	{
		Course course = courseService.findById(7L);
		if (course != null)
			courseService.deleteById(course.getId());
	}
	
	@Test
	public void deleteAll ()
	{
		courseService.deleteAll();
	}
}
