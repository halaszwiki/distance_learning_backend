package com.example.dl.payload;

import com.example.dl.model.Exam;

public class ExamRequest {
    private Long userId;
    private Exam exam;

    public ExamRequest(Long userId, Exam exam){
        this.userId = userId;
        this.exam = exam;
    }

    public Long getUserId(){ return this.userId;}

    public Exam getExam(){return this.exam;}
}
