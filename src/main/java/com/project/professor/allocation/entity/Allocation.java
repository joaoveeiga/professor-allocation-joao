package com.project.professor.allocation.entity;

import java.time.DayOfWeek;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "allocation")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Allocation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ToString.Exclude
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(name = "day", nullable = false, length = 10)
	private DayOfWeek dayOfWeek;

	@Temporal(TemporalType.TIME)
	@Column(name = "start", nullable = false)
	private Date startHour;

	@Temporal(TemporalType.TIME)
	@Column(name = "end", nullable = false)
	private Date endHour;

	@ManyToOne(optional = false)
	@JoinColumn(name = "professor_id", nullable = false)
	private Professor professor;

	@ManyToOne(optional = false)
	@JoinColumn(name = "course_id", nullable = false)
	private Course course;
}