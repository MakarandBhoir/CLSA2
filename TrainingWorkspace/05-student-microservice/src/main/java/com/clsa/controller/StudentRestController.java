package com.clsa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.clsa.model.Course;
import com.clsa.model.Student;
import com.clsa.service.StudentService;

@RestController
public class StudentRestController {
	@Autowired
	private RestTemplate template;
	@Autowired
	private DiscoveryClient discoveryClient;
	
	@Autowired
	private StudentService studentService;
	
	// http://localhost:9095/students/101
	@GetMapping("/students/{studentId}")
	public Student getStudentById(@PathVariable("studentId") Integer studentId) {
		Student student = studentService.findStudentById(studentId);
		//List<Course> courses = template.getForObject("http://localhost:9094/courses/student/" + studentId, List.class);
		//student.setCourse(courses);
		
		List<ServiceInstance> serviceInstances = discoveryClient.getInstances("COURSE-SERVICE");
		
		int min = 0;
        int max = serviceInstances.size();
        int randomNum = min + (int)(Math.random() * ((max - min))); 
		
		ServiceInstance serviceInstance = serviceInstances.get(randomNum);
		String url = serviceInstance.getUri() + "/courses/student/" + studentId;
		List<Course> courses = template.getForObject(url, List.class);
		student.setCourse(courses);
		
		return student;
	}
}










