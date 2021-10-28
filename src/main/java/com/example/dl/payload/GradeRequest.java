package com.example.dl.payload;

public class GradeRequest {
    private Long userId;
    private int grade;

    public GradeRequest(Long userId, int grade) {
        this.userId = userId;
        this.grade = grade;
    }

    GradeRequest(){}

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
