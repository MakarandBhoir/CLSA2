package com.clsa.service;

import java.util.List;

import com.clsa.entities.Student;
import com.clsa.exceptions.DuplicateStudentException;

public interface StudentService {
	public List<Student> findAllStudents();
	public boolean addStudent(Student student)throws DuplicateStudentException;
}
