package com.example.dl.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Setter
@Getter
@Table(	name = "users",
		uniqueConstraints = {
				@UniqueConstraint(columnNames = "username"),
		})
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long user_id;
	private String username;
	private String email;
	private String password;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(	name = "user_role",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<Role> roles = new ArrayList<Role>();
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "taken_courses", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "course_id"))
	@JsonIgnoreProperties("users")
	private List<Course> courses = new ArrayList<>();
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "taken_exams", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "exam_id"))
	@JsonIgnoreProperties(value = "users", allowSetters = true)
	private List<Exam> exams = new ArrayList<>();
	@OneToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "grades_table", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "grade_id"))
	@JsonIgnoreProperties(value = "userId", allowSetters = true)
	private List<Grade> grades = new ArrayList<>();


	public User(User user) {
		this.user_id = user.user_id;
		this.username = user.username;
		this.email = user.email;
		this.password = user.password;
		this.roles = user.roles;
		this.courses = user.courses;
		this.exams = user.exams;
		this.grades = user.grades;
	}

	public User(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public User() {
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setPassword(String password) {
		this.password = password;
	}

    public void setRoles(List<Role> roles) { this.roles = roles; }

    public void addCourse(Course course){
		courses.add(course);
	}

	public List<Course> getCourses(){
		return this.courses;
	}

	public void addExam(Exam exam){
		exams.add(exam);
	}

	public List<Exam> getExams(){
		return this.exams;
	}

	public List<Grade> getGrades() {
		return grades;
	}

	public void setGrades(List<Grade> grades) {
		this.grades = grades;
	}
}
