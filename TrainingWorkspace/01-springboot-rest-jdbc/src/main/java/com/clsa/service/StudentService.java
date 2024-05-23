package com.clsa.service;

import java.util.List;

import com.clsa.entities.Course;
import com.clsa.entities.Student;
import com.clsa.exceptions.DuplicateStudentException;
import com.clsa.exceptions.StudentNotFoundException;

public interface StudentService {
	public List<Student> findAllStudents();
	public boolean addStudent(Student student)throws DuplicateStudentException;
	public boolean modifyStudent(Student student);
	public boolean removeStudent(int studentId)throws StudentNotFoundException;
	public Student findStudentById(int studentId)throws StudentNotFoundException;
	public List<Course> findCoursesByStudentId(int studentId)throws StudentNotFoundException;
}
