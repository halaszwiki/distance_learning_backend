package com.example.dl.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name="courses")
public class Course {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	String name;
	int semester;
	String degreeLevel;
	@ManyToMany(mappedBy = "courses")
	List<User> users = new ArrayList<>();
}

