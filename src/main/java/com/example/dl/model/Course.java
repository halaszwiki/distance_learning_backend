package com.example.dl.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
	String program;
	String[] days;
	int start;
	int end;
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "courses")
	@JsonIgnoreProperties("courses")
	List<User> users = new ArrayList<>();

	public int getStart(){return start;}
	public int getEnd(){return end;}
}

