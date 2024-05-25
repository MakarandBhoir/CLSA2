package com.clsa.service;

import java.util.List;

import com.clsa.model.Student;

public interface StudentService {
	public List<Student> findAllStudents();
	public Student findStudentById(Integer studentId);
}
