package com.example.dl.service;

import com.example.dl.model.Exam;

import com.example.dl.repository.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    ExamRepository examRepository;

    @Override
    public List<Exam> findAll() {
        return examRepository.findAll();
    }

    @Override
    public Exam save(Exam exam) {
        examRepository.save(exam);
        return exam;
    }

    @Override
    public Exam findById(Long id) {
        if(examRepository.findById(id).isPresent()) {
            return examRepository.findById(id).get();
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        Exam exam = findById(id);
        examRepository.delete(exam);
    }
}
