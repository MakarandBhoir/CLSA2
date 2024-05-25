package com.clsa.feignproxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.clsa.model.Course;

@FeignClient(name = "COURSE-MICROSERVICE", path = "/courses")
public interface CourseServiceProxy {
	@GetMapping(path="student/{stuId}")
	public List<Course> getCoursesByStudentId(@PathVariable("stuId") int studentId);
}
