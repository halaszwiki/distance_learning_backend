package com.example.dl.controller;


import com.example.dl.model.Comment;
import com.example.dl.model.Course;
import com.example.dl.payload.CommentRequest;
import com.example.dl.service.CommentService;
import com.example.dl.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @Autowired
    CourseService courseService;

    @PostMapping("/addComment")
    public ResponseEntity<?> addComment(@RequestBody CommentRequest commentRequest){

        Course course = courseService.findById(commentRequest.getCourseId());

        Comment comment = new Comment();
        comment.setUsername(commentRequest.getUsername());
        comment.setComment(commentRequest.getComment());
        comment.setCourse(course);

        course.getComments().add(comment);

        commentService.save(comment);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Comment>> getComments(@PathVariable("id") Long id){
        List<Comment> comments = commentService.findAllByCourseId(id);
        return new ResponseEntity<List<Comment>>(comments, HttpStatus.OK);
    }
}
