package com.example.dl.payload;

import com.example.dl.model.Course;


public class CourseToUserRequest {
   private Long userId;
   private Course course;

   public CourseToUserRequest(Long userId, Course course){
       this.userId = userId;
       this.course = course;
   }

    public Long getUserId(){ return this.userId;}

    public Course getCourse(){return this.course;}
}
