package com.example.dl.service;

import com.example.dl.model.Comment;
import com.example.dl.model.Course;
import com.example.dl.repository.CommentRepository;
import com.example.dl.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    CommentRepository commentRepository;
    @Autowired
    CourseRepository courseRepository;

    @Override
    public List<Comment> findAllByCourseId(Long id) {
        Course course = null;
        if(courseRepository.findById(id).isPresent()) {
            course = courseRepository.findById(id).get();
        }
        return commentRepository.findByCourse(course);
    }

    @Override
    public Comment save(Comment comment) {
        commentRepository.save(comment);
        return comment;
    }
}
