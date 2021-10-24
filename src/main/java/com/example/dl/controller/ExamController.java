package com.example.dl.controller;

import com.example.dl.model.Exam;
import com.example.dl.model.User;
import com.example.dl.payload.ExamRequest;
import com.example.dl.service.ExamService;
import com.example.dl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/exam")
public class ExamController {

    @Autowired
    ExamService examService;
    @Autowired
    UserService userService;


    @GetMapping("/list")
    public ResponseEntity<List<Exam>> getAllExam(){
        List<Exam> exams = examService.findAll();
        return new ResponseEntity<List<Exam>>(exams, HttpStatus.OK);
    }

    @PostMapping("/list")
    public ResponseEntity<?> createExam(@RequestBody Exam exam){
        User user = userService.findById(exam.getCreatorId());
        user.getExams().add(exam);

        examService.save(exam);
        
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Exam> get(@PathVariable("id") Long id){
        Exam exam = examService.findById(id);
        return new ResponseEntity<Exam>(exam, HttpStatus.OK);
    }

    @PostMapping("/addExamToUser")
    public ResponseEntity<?> addExamToUser(@RequestBody ExamRequest examRequest){
        User user = userService.findById(examRequest.getUserId());
        user.getExams().add(examRequest.getExam());
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<List<User>> GetUsersOnExam(@PathVariable("id") Long id){
        Exam exam = examService.findById(id);
        List<User> users = exam.getUsers();
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }
}
