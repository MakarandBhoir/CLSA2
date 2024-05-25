package com.clsa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clsa.dto.StudentDTO;
import com.clsa.jwt.AuthenticationRequest;
import com.clsa.jwt.AuthenticationResponse;
import com.clsa.jwt.JwtUtil;
import com.clsa.jwt.MyUserDetailsService;
import com.clsa.model.Student;
import com.clsa.service.StudentService;

@RestController
@RequestMapping(path="students")
@CrossOrigin
public class StudentController {
	@Autowired
	private StudentService service;
	
	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private MyUserDetailsService userDetailsService;

	// http://localhost:9091/student-api/students/
	@GetMapping(path="/")
	public ResponseEntity<List<Student>> getAllStudents(){
		ResponseEntity<List<Student>> response = null;
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		List<Student> students = service.findAllStudents();
		response = new ResponseEntity<List<Student>>(students, HttpStatus.OK);
		return response;
	}
	
	// http://localhost:9091/student-api/students/10
	@GetMapping(path="{studentId}")
	public ResponseEntity<StudentDTO> getStudentById(@PathVariable("studentId") int studentId) {
		ResponseEntity<StudentDTO> response = null;
		Student student = service.findStudentById(studentId);
		if(student != null) {
			StudentDTO dto = new StudentDTO(student.getStudentId(), student.getStudentName(), student.getScore(), student.getAddress().getCity(), student.getAddress().getState(), student.getAddress().getPin());
			response = new ResponseEntity<StudentDTO>(HttpStatus.OK);
		}
		else {
			response = new ResponseEntity<StudentDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
	
	// http://localhost:9091/student-api/students
	@PostMapping(path="/")
	public ResponseEntity<String> addStudent(@RequestBody Student student) {
		ResponseEntity<String> response = null;
		boolean result = service.addStudent(student);
		if(result)
			response = new ResponseEntity<String>("Student data is added in database", HttpStatus.CREATED);
		else
			response = new ResponseEntity<String>("Problem saving student data in database", HttpStatus.INTERNAL_SERVER_ERROR);
		return response;
	}
	
	// http://localhost:9091/student-api/students/160
	@DeleteMapping(path="{studentId}")
	public void deleteStudentByStudentId(@PathVariable("studentId") int studentId) {
		service.removeStudent(studentId);
	}

	// http://localhost:9091/student-api/students/
	@PutMapping(path="/")
	public Student updateStudent(@RequestBody Student student) {
		boolean result = service.modifyStudent(student);
		if(result)
			student = service.findStudentById(student.getStudentId());
		return student;
	}
	
	// http://localhost:9091/student-api/students/login.do
	@PostMapping(path = "login.do")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
			);
		}
		catch (BadCredentialsException e) {
			e.printStackTrace();
			throw new Exception("Incorrect username or password", e);
		}


		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

		// assuming credintial are correct will perform following step
		final String jwt = jwtTokenUtil.generateToken(userDetails);
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
}
