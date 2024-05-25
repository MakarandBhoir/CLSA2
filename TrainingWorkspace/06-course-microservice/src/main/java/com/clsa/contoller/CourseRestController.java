package com.clsa.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.clsa.model.Course;
import com.clsa.service.CourseService;

@RestController
public class CourseRestController {
	@Autowired
	private CourseService courseService;
	
	@Autowired
	Environment env;
	
	// http://localhost:9094/courses/student/101
	@GetMapping("/courses/student/{studentId}")
	public List<Course> getCoursesByStudentId(@PathVariable("studentId") int studentId) {
		System.out.println(env.getProperty("local.server.port"));
		return courseService.findCoursesByStudentId(studentId);
	}
}
