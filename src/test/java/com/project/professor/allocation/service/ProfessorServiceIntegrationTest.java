package com.project.professor.allocation.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.project.professor.allocation.entity.Department;
import com.project.professor.allocation.entity.Professor;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class ProfessorServiceIntegrationTest 
{
	@Autowired
	ProfessorService professorService;
	
	@Test
	public void createSave()
	{
		Department department = new Department(8L, null, null);
		
		Professor matheus = new Professor(null, "Matheus", "0", department, null);
		Professor amirton = new Professor(null, "Amirton", "1", department, null);
		Professor felipe = new Professor(null, "Felipe", "2", department, null);
		Professor tiago = new Professor(null, "Tiago", "3", department, null);
		Professor rafael = new Professor(null, "Rafael", "4", department, null);
		Professor endrigo = new Professor(null, "Endrigo", "5", department, null);
		
		matheus = professorService.createSave(matheus);
		amirton = professorService.createSave(amirton);
		felipe = professorService.createSave(felipe);
		tiago = professorService.createSave(tiago);
		rafael = professorService.createSave(rafael);
		endrigo = professorService.createSave(endrigo);
	}
	
	@Test
	public void findById()
	{
		Long id = 8L;
		
		System.out.println(professorService.findById(id));
	}
	
	@Test
	public void findAll ()
	{
		List<Professor> professors = professorService.findAll(null);
		
		professors.forEach(System.out::println);
	}
	
	@Test
	public void findByName ()
	{
		String name = "a";
		
		List<Professor> professors = professorService.findAll(name);
		
		System.out.println("Professores os quais possuem a letra 'a' no nome: ");
		professors.forEach(System.out::println);
		
		name = "e";
		System.out.println();
		System.out.println("Professores os quais possuem a letra 'e' no nome: ");
		professors = professorService.findAll(name);
		professors.forEach(System.out::println);
	}
	
	@Test
	public void findByCpf ()
	{
		String cpf = "3";
		
		System.out.println(professorService.findByCpf(cpf));
	}
	
	@Test
	public void findByDepartmentId ()
	{
		Long departmentId = 7L;
		
		List<Professor> professors = professorService.findByDepartmentId(departmentId);
		
		professors.forEach(System.out::println);
	}
	
	@Test
	public void findAll_()
	{
		String name = "";
		
		List<Professor> professors = professorService.findAll(name);
		
		professors.forEach(System.out::println);
	}
	
	@Test
	public void updateSave ()
	{
		Professor professor = professorService.findById(10L);
		
		if (professor != null)
		{
			professor.setCpf("42");
			
			professor = professorService.updateSave(professor);
		}
		
	}
	
	@Test
	public void deleteById ()
	{
		Professor professor = professorService.findById(10L);
		
		professorService.deleteById(professor.getId());
	}
	
	@Test
	public void deleteAll ()
	{
		professorService.deleteAll();
	}
}
