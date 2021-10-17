package com.example.dl.repository;

import com.example.dl.model.Comment;
import com.example.dl.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByCourse(Course course);
}