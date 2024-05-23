package com.clsa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clsa.dao.StudentDao;
import com.clsa.entities.Course;
import com.clsa.entities.Student;
import com.clsa.exceptions.DuplicateStudentException;
import com.clsa.exceptions.StudentNotFoundException;

@Service
public class StudentServiceImpl implements StudentService{
	@Autowired
	private StudentDao studentDao;
	
	@Override
	public List<Student> findAllStudents() {
		return studentDao.readAllStudents();
	}
	@Override
	public boolean addStudent(Student student) throws DuplicateStudentException {
		int result = studentDao.createStudent(student);
		return (result==1)? true : false;
	}
	@Override
	public boolean modifyStudent(Student student) {
		return studentDao.updateStudent(student)==1?true:false;
	}
	@Override
	public boolean removeStudent(int studentId) throws StudentNotFoundException {
		return studentDao.deleteStudent(studentId)==1?true:false;
	}
	@Override
	public Student findStudentById(int studentId) throws StudentNotFoundException {
		return studentDao.readStudentById(studentId);
	}
	@Override
	public List<Course> findCoursesByStudentId(int studentId) throws StudentNotFoundException {
		return studentDao.readCoursesByStudentId(studentId);
	}
}




