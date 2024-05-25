package com.clsa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clsa.dao.StudentDao;
import com.clsa.entities.Student;
import com.clsa.exceptions.DuplicateStudentException;

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
}




