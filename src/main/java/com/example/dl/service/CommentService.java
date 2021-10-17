package com.example.dl.service;

import com.example.dl.model.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> findAllByCourseId(Long id);

    Comment save(Comment comment);
}
