package com.example.dl.payload;

public class GradeRequest {
    private String username;
    private Long courseId;
    private int grade;

    public GradeRequest(String username, Long courseId, int grade) {
        this.username = username;
        this.courseId = courseId;
        this.grade = grade;
    }

    GradeRequest(){}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
