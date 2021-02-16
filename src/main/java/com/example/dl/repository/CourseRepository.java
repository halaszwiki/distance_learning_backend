package com.example.dl.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.dl.model.Course;




@Repository
public interface CourseRepository extends JpaRepository<Course, Long>{

}
