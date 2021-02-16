package com.example.dl.service;

import java.util.List;

import com.example.dl.model.Course;

public interface CourseService {

	List<Course> findAll();

	Course save(Course subject);
	
	Course findById(Long id);
	
	void delete(Long id);

}
