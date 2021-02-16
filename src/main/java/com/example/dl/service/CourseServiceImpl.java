package com.example.dl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dl.model.Course;
import com.example.dl.repository.CourseRepository;

@Service
public class CourseServiceImpl implements CourseService{
	
	@Autowired
	CourseRepository courseRepository;

	@Override
	public List<Course> findAll() {
		return courseRepository.findAll();
	}

	@Override
	public Course save(Course subject) {
		courseRepository.save(subject);
		return subject;
	}

	@Override
	public Course findById(Long id) {
		if(courseRepository.findById(id).isPresent()) {
			return courseRepository.findById(id).get();
		}
		return null;
	}

	@Override
	public void delete(Long id) {
		Course course = findById(id);
		courseRepository.delete(course);
		
	}

}
