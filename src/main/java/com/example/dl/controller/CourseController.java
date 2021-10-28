package com.example.dl.controller;

import java.util.ArrayList;
import java.util.List;

import com.example.dl.model.Comment;
import com.example.dl.model.MyUserDetails;
import com.example.dl.model.User;
import com.example.dl.payload.CourseRequest;
import com.example.dl.payload.MessageResponse;
import com.example.dl.service.MyUserDetailsService;
import com.example.dl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
	public ResponseEntity<?> addCourseToUser(@RequestBody CourseRequest courseRequest){
		User user = userService.findById(courseRequest.getUserId());
		Course course = courseRequest.getCourse();
		user.getCourses().add(course);

		userService.save(user);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/myCourses/{id}")
	public ResponseEntity<List<Course>> getCreatedCourses(@PathVariable("id") Long id) {
		List<Course> courses = courseService.findAll();
		List<Course> myCourses = new ArrayList<>();
		for(Course c: courses){
			if(c.getCreatorId().equals(id)){
				myCourses.add(c);
			}
		}
		return new ResponseEntity<List<Course>>(myCourses, HttpStatus.OK);
	}

	@GetMapping("/users/{id}")
	public ResponseEntity<List<User>> getAllUsersOnCourse(@PathVariable("id") Long id){
		Course course = courseService.findById(id);
		List<User> users = course.getUsers();
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
}
