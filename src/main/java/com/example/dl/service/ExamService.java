package com.example.dl.service;


import com.example.dl.model.Exam;

import java.util.List;

public interface ExamService {

    List<Exam> findAll();

    Exam save(Exam exam);

    Exam findById(Long id);

    void delete(Long id);

}