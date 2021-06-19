package com.project.professor.allocation.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "professor")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Professor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// Local errado
	//@ToString.Exclude
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "cpf", unique = true, nullable = false)
	private String cpf;

	@ManyToOne(optional = false)
	@JoinColumn(name = "department_id", nullable = false)
	private Department department;

	// Local correto. O motivo está no final do slide
	@ToString.Exclude
    @EqualsAndHashCode.Exclude
	@OnDelete(action = OnDeleteAction.CASCADE)
	@OneToMany(mappedBy = "professor")
	private List<Allocation> allocations;
}