package com.clsa.service;

import java.util.List;

import com.clsa.model.Course;

public interface CourseService {
	public List<Course> findCoursesByStudentId(int studentId);
}
