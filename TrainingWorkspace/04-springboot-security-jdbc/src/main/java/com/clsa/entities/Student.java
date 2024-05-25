package com.clsa.entities;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

@Component("student")
@Scope(scopeName="prototype")
public class Student {
	private int studentId;
	@NotEmpty
	private String studentName;
	@Min(value = 0)
	@Max(value = 100)
	private double score;
	
	@Autowired
	private Address address;
	
	private List<Course> course;
		
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public List<Course> getCourse() {
		return course;
	}
	public void setCourse(List<Course> course) {
		this.course = course;
	}

	public void showAddress() {
		System.out.println(address);
	}
	public void showStudent() {
		System.out.println("Student Id: "+studentId);
		System.out.println("Student Name: "+studentName);
		System.out.println("Score: "+score);
		showAddress();
	}
	public Student() {
		
	}
	
	public Student(int studentId, String studentName, double score) {
		super();
		this.studentId = studentId;
		this.studentName = studentName;
		this.score = score;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", studentName=" + studentName + ", score=" + score
				+ "]";
	}
}
