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
	private static final String DELETE_STUDENT_BY_ID = "Delete From STUDENT Where STUDENT_ID = ?";
	private static final String UPDATE_STUDENT = "Update STUDENT set STUDENT_NAME = ?, STUDENT_SCORE = ?, CITY = ?, STATE = ?, PIN = ? Where STUDENT_ID = ?";
	private static final String FIND_COURSES_BY_STUDENT_ID = "Select COURSE_ID, COURSE_NAME, COURSE_FEES FROM STUDENTCOURSES WHERE STUDENT_ID = ?";

	@Override
	public int createStudent(Student student) throws DuplicateStudentException {
		int result = 0;
		try {
			result = jdbcTemplate.update(CREATE_STUDENT, student.getStudentId(), student.getStudentName(),
					student.getScore(), student.getAddress().getCity(), student.getAddress().getState(),
					student.getAddress().getPin());
		} catch (Exception e) {
			throw new DuplicateStudentException("Student with Id: " + student.getStudentId() + " already exists", e);
		}
		return result;
	}

	@Override
	public List<Student> readAllStudents() {
		List<Student> studentList = jdbcTemplate.query(READ_ALL_STUDENTS, (rs, i) -> {
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
		int result = jdbcTemplate.update(UPDATE_STUDENT, student.getStudentName(), student.getScore(),
				student.getAddress().getCity(), student.getAddress().getState(), student.getAddress().getPin(),
				student.getStudentId());
		return result;
	}

	@Override
	public int deleteStudent(int studentId) throws StudentNotFoundException {
		int result = jdbcTemplate.update(DELETE_STUDENT_BY_ID, studentId);
		return result;
	}

	@Override
	public Student readStudentById(int studentId) throws StudentNotFoundException {
		Student result = null;
		try {
			result = jdbcTemplate.queryForObject("Select * From Student Where Student_Id = ?", (rs, i) -> {
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
			}, studentId);
		} catch (RuntimeException e) {
			throw new StudentNotFoundException("Student with Id: " + studentId + " not found", e);
		}
		return result;
	}

	@Override
	public List<Student> readStudentByMinAndMaxScore(double min, double max) {
		return jdbcTemplate.query("Select * From Student Where Student_Score Between ? And ?", (rs, i) -> {
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
		}, min, max);
	}

	@Override
	public List<Course> readCoursesByStudentId(int studentId) throws StudentNotFoundException {
		List<Course> courses = jdbcTemplate.query(FIND_COURSES_BY_STUDENT_ID, (rs, i) -> {
			Course course = new Course();
			course.setCourseId(rs.getInt(1));
			course.setCourseName(rs.getString(2));
			course.setCourseFees(rs.getDouble(3));
			return course;
		}, studentId);
		return courses;
	}
}
