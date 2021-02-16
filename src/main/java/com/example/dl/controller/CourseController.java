package com.example.dl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dl.model.Course;
import com.example.dl.service.CourseService;

@CrossOrigin("*")
@RestController
@RequestMapping("/course")
public class CourseController {
	
	@Autowired
	CourseService courseService;
	
	@GetMapping("/list")
	public ResponseEntity<List<Course>> getAllSubject(){
		List<Course> subjects = courseService.findAll();
		return new ResponseEntity<List<Course>>(subjects, HttpStatus.OK);
	}
	
	@PostMapping("/list")
	public ResponseEntity<Course> save(@RequestBody Course subject){
		Course saved = courseService.save(subject);
		return new ResponseEntity<Course>(saved, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Course> get(@PathVariable("id") Long id){
		Course course = courseService.findById(id);
		return new ResponseEntity<Course>(course, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") Long id){
		courseService.delete(id);
		return new ResponseEntity<String>("Deleted successfully!", HttpStatus.OK);
	}

}
