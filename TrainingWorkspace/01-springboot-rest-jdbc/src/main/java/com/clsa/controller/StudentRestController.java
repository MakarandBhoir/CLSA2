package com.clsa.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.clsa.entities.Course;
import com.clsa.entities.Student;
import com.clsa.exceptions.DuplicateStudentException;
import com.clsa.exceptions.StudentNotFoundException;
import com.clsa.service.StudentService;

@RestController
@RequestMapping("/student-api")
public class StudentRestController {
	@Autowired
	private StudentService studentService;
	
	// Method - Get, Url- http://localhost:9091/student-api
	@GetMapping
	public List<Student> getAllStudents() {
		return studentService.findAllStudents();
	}
	
	// Method - Post, Url- http://localhost:9091/student-api
	@PostMapping
	public ResponseEntity<String> addStudent(@RequestBody Student student) throws DuplicateStudentException{
		ResponseEntity<String> response = null;
		boolean result = studentService.addStudent(student);
		if (result) {
			response = new ResponseEntity<String>("Student added successfully", HttpStatus.CREATED);
		} else {
			response = new ResponseEntity<String>("Student not added", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
	
	// Method - Get, Url- http://localhost:9091/student-api/1
	@GetMapping(path = "{studentId}")
	public Student getStudentById(@PathVariable("studentId") Integer studentId) throws StudentNotFoundException {
		return studentService.findStudentById(studentId);
	}
	
	// Method - Delete, Url- http://localhost:9091/student-api/1
	@DeleteMapping(path = "{studentId}")
	public ResponseEntity<String> removeStudent(@PathVariable("studentId") Integer studentId)
			throws StudentNotFoundException {
		ResponseEntity<String> response = null;
		boolean result = studentService.removeStudent(studentId);
		if (result) {
			response = new ResponseEntity<String>("Student removed successfully", HttpStatus.OK);
		} else {
			response = new ResponseEntity<String>("Student not removed", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
	// Method - Get, http://localhost:9091/student-api/courses/100
	@GetMapping(path = "courses/{studentId}")
	public List<Course> getCourseByStudentId(@PathVariable("studentId") Integer studentId) throws StudentNotFoundException{
		return studentService.findCoursesByStudentId(studentId);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}
}




