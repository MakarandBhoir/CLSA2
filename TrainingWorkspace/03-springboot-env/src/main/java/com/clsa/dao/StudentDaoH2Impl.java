package com.clsa.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.clsa.entities.Address;
import com.clsa.entities.Course;
import com.clsa.entities.Student;
import com.clsa.exceptions.DuplicateStudentException;
import com.clsa.exceptions.StudentNotFoundException;

@Repository
@Profile("dev")
public class StudentDaoH2Impl implements StudentDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static final String READ_ALL_STUDENTS = "Select * From Student";
	private static final String CREATE_STUDENT = "Insert into Student(Student_Id, Student_Name, Student_Score, city, state, pin) values(?,?,?,?,?,?)";
	
	
	@Override
	public int createStudent(Student student) throws DuplicateStudentException {
		int result = 0;
		try {
		result = jdbcTemplate.update(CREATE_STUDENT, student.getStudentId(), student.getStudentName(), 
				student.getScore(), student.getAddress().getCity(), student.getAddress().getState(), 
				student.getAddress().getPin());		
		}
		catch (Exception e) {
			throw new DuplicateStudentException("Student with Id: " + student.getStudentId() + " already exists", e);
		}
		return result;
	}
	@Override
	public List<Student> readAllStudents() {
		List<Student> studentList =  jdbcTemplate.query(READ_ALL_STUDENTS, (rs, i)->{
			Student student = new Student();
			Address address = new Address();
			student.setStudentId(rs.getInt("Student_Id"));
			student.setStudentName(rs.getString("Student_Name"));
			student.setScore(rs.getDouble("Student_Score"));
			address.setCity(rs.getString("city"));
			address.setState(rs.getString("state"));
			address.setPin(rs.getString("pin"));
			student.setAddress(address);
			return student;
		});
		return studentList;
	}
	@Override
	public int updateStudent(Student student) {
		return 0;
	}
	@Override
	public int deleteStudent(int studentId) throws StudentNotFoundException {
		return 0;
	}
	@Override
	public Student readStudentById(int studentId) throws StudentNotFoundException {
		return null;
	}
	@Override
	public List<Student> readStudentByMinAndMaxScore(double min, double max) {
		return null;
	}
	@Override
	public List<Course> readCoursesByStudentId(int studentId) throws StudentNotFoundException {
		return null;
	}
}
