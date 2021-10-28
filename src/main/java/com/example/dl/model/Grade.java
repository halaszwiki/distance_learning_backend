package com.example.dl.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name="grades")
public class Grade {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    int grade;
    private Long courseId;
    @ManyToOne
    @JsonIgnoreProperties(value = "grades", allowSetters = true)
    private User user;

    public Grade(int grade, User user, Long courseId) {
        this.grade = grade;
        this.user = user;
        this.courseId = courseId;
    }

    Grade(){}

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }
}
