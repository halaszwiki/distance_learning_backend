package com.example.dl.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.example.dl.model.Grade;
import com.example.dl.model.MyUserDetails;
import com.example.dl.service.CourseService;
import com.example.dl.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.example.dl.model.User;
import com.example.dl.service.UserService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	UserService userService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtUtil jwtUtil;

	@GetMapping("/welcome")
	public String welcome() {
		return "Welcome";
	}

	@GetMapping("/list")
	public ResponseEntity<List<User>> getAllUser() {
		List<User> users = userService.findAll();
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	@PostMapping("/list")
	public ResponseEntity<?> save(@RequestBody User user){
		User saved = userService.save(user);
		Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return new ResponseEntity<User>(saved, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> get(@PathVariable("id") Long id){
		User user = userService.findById(id);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") Long id){
		userService.delete(id);
		return new ResponseEntity<String>("Deleted successfully!", HttpStatus.OK);
	}

	@PostMapping("/addGarde")
	public ResponseEntity<?> addGradeToUser(@RequestBody Grade grade){
		User user = grade.getUser();
		user.getGrades().add(grade);
		userService.save(user);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/{id}/grades")
	public ResponseEntity<List<Grade>> getGradesFromUser(@PathVariable("id") Long id) {
		User user = userService.findById(id);
		List<Grade> grades = user.getGrades();
		return new ResponseEntity<List<Grade>>(grades, HttpStatus.OK);
	}

}
