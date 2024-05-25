package com.clsa.dao;

import java.util.List;

import com.clsa.entities.Course;
import com.clsa.entities.Student;
import com.clsa.exceptions.DuplicateStudentException;
import com.clsa.exceptions.StudentNotFoundException;

public interface StudentDao {
	public int createStudent(Student student)throws DuplicateStudentException;
	public List<Student> readAllStudents();
	public int updateStudent(Student student);
	public int deleteStudent(int studentId)throws StudentNotFoundException;
	public Student readStudentById(int studentId)throws StudentNotFoundException;
	public List<Student> readStudentByMinAndMaxScore(double min, double max);
	public List<Course> readCoursesByStudentId(int studentId)throws StudentNotFoundException;
}
