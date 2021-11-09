package com.example.dl.service;
import com.example.dl.model.Grade;
import com.example.dl.repository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GradeServiceImpl implements GradeService {

    @Autowired
    GradeRepository gradeRepository;

    @Override
    public Grade save(Grade grade) {
        gradeRepository.save(grade);
        return grade;
    }
}
