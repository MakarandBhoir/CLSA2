package com.clsa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clsa.entities.Student;
import com.clsa.exceptions.DuplicateStudentException;
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
}




