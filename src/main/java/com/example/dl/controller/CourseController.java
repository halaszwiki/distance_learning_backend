package com.example.dl.controller;

import java.util.List;

import com.example.dl.model.MyUserDetails;
import com.example.dl.model.User;
import com.example.dl.payload.CourseToUserRequest;
import com.example.dl.payload.MessageResponse;
import com.example.dl.service.MyUserDetailsService;
import com.example.dl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

import javax.transaction.Transactional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/course")
public class CourseController {
	
	@Autowired
	CourseService courseService;
	@Autowired
	UserService userService;
	@Autowired
	private MyUserDetailsService userDetailsService;

	
	@GetMapping("/list")
	public ResponseEntity<List<Course>> getAllSubject(){
		List<Course> subjects = courseService.findAll();
		return new ResponseEntity<List<Course>>(subjects, HttpStatus.OK);
	}
	
	@PostMapping("/list")
	public ResponseEntity<?> save(@RequestBody Course subject){
		Course saved = courseService.save(subject);
		if(saved.getStart() < saved.getEnd()) {
			return new ResponseEntity<Course>(saved, HttpStatus.OK);
		}
		else{
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Course ends earlier than starts!"));
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Course> get(@PathVariable("id") Long id){
		Course course = courseService.findById(id);
		return new ResponseEntity<Course>(course, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") Long id){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
		User user = userDetails.getUser();

		Course course = courseService.findById(id);
		user.getCourses().remove(course);

		courseService.delete(id);
		return new ResponseEntity<String>("Deleted successfully!", HttpStatus.OK);
	}

	@PostMapping("/addCourseToUser")
	public ResponseEntity<?> addCourseToUser(@RequestBody CourseToUserRequest courseToUserRequest){


		User user = userService.findById(courseToUserRequest.getUserId());
		Course course = courseToUserRequest.getCourse();
		user.getCourses().add(course);

		userService.save(user);

		return new ResponseEntity<>(HttpStatus.OK);
	}
}
