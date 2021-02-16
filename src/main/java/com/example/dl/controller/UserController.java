package com.example.dl.controller;

import java.util.List;
import java.util.Optional;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.example.dl.repository.UserRepository;
import com.example.dl.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.dl.model.User;
import com.example.dl.service.UserService;

import javax.swing.text.html.Option;

@CrossOrigin("*")
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;


	@GetMapping("/welcome")
	public String welcome() {
		return "Welcome";
	}

	@GetMapping("/list")
	public ResponseEntity<List<User>> getAllUser() {
		List<User> users = userService.findAll();
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
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
}
