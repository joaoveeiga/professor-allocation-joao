package com.project.professor.allocation.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import com.project.professor.allocation.entity.Professor;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")
public class ProfessorRepositoryTest {

	@Autowired
	private ProfessorRepository professorRepository;
	
	@Test
	public void findAll ()
	{
		//act
		List<Professor> professors = professorRepository.findAll();
		
		//print
		System.out.println(professors);
	}
	
	@Test
	void findById ()
	{
		//Arrange
		Long id = 1L;
		
		//act
		Professor professor = professorRepository.findById(id).orElse(null);

		//Print
		System.out.println(professor);
	}
	
	@Test
	void findByNameContainingIgnoreCase ()
	{
		//Arrange
		String name = "B";
		
		//act
		List<Professor> professors = professorRepository.findByNameContainingIgnoreCase(name);
		
		//Print
		System.out.println(professors);
		
	}
	
	@Test
	void findByCpf ()
	{
		//Arrange
		String cpf = "1";
		
		//Act
		Professor professor = professorRepository.findByCpf(cpf).orElse(null);
		
		//Print
		System.out.println(professor);
	}
}
