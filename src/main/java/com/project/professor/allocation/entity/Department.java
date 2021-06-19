package com.project.professor.allocation.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "department")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// Local errado
	//@ToString.Exclude
	private Long id;

	@Column(name = "name", unique = true, nullable = false)
	private String name;

	// Local correto. O motivo está no final do slide
	@ToString.Exclude
    @EqualsAndHashCode.Exclude
	@OnDelete(action = OnDeleteAction.CASCADE)
	@OneToMany(mappedBy = "department")
	private List<Professor> professors;
}