package com.example.dl.controller;

import com.example.dl.model.Exam;
import com.example.dl.model.Grade;
import com.example.dl.model.RoleEnum;
import com.example.dl.model.User;
import com.example.dl.payload.ExamRequest;
import com.example.dl.payload.GradeRequest;
import com.example.dl.repository.UserRepository;
import com.example.dl.service.ExamService;
import com.example.dl.service.GradeService;
import com.example.dl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/exam")
public class ExamController {

    @Autowired
    ExamService examService;
    @Autowired
    UserService userService;
    @Autowired
    GradeService gradeService;
    @Autowired
    UserRepository userRepository;


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
        boolean foundIt = false;
       for(Exam e: user.getExams()) {
           if (e.getId().equals(examRequest.getExam().getId())) {
               foundIt = true;
               break;
           }
       }
       if(!foundIt){
           user.getExams().add(examRequest.getExam());
       }
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<List<User>> GetUsersOnExam(@PathVariable("id") Long id){
        Exam exam = examService.findById(id);
        List<User> users = exam.getUsers();

        List<User> students = new ArrayList<>();
        for(User u: users){
            if (u.getRoles().size() == 1) {
                students.add(u);
            }
        }

        return new ResponseEntity<List<User>>(students, HttpStatus.OK);
    }

    @GetMapping("/users/{id}/grade")
    public ResponseEntity<List<User>> GradableStudents(@PathVariable("id") Long id) {
        Exam exam = examService.findById(id);
        List<User> users = exam.getUsers();

        List<User> students = new ArrayList<>();
        List<User> gradableStudents = new ArrayList<>();

        for (User u : users) {
            if (u.getRoles().size() == 1) {
                students.add(u);
            }
        }

        if(!students.isEmpty()) {
            for (User s : students) {
                for (Grade g : s.getGrades()) {
                    if (g.getCourseId().equals(exam.getCourseId())) {
                        gradableStudents.add(s);
                    }
                }
            }
        }

        return new ResponseEntity<List<User>>(gradableStudents, HttpStatus.OK);
    }

    @PostMapping("/addGrade")
    public ResponseEntity<?> addGradeToStudent(@RequestBody GradeRequest gradeRequest){
        User user = userRepository.findByUsername(gradeRequest.getUsername());
        Grade grade = new Grade(gradeRequest.getGrade(), user, gradeRequest.getCourseId());
        gradeService.save(grade);

        user.getGrades().add(grade);
        userService.save(user);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/removeFromExam")
    public ResponseEntity<?> removeUserFromExam(@RequestBody ExamRequest examRequest){
        User user = userService.findById(examRequest.getUserId());
        Exam exam = examService.findById(examRequest.getExam().getId());
        user.getExams().remove(exam);
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
