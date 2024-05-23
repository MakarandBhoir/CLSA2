package com.clsa.exceptions;

public class StudentNotFoundException extends Exception {

	public StudentNotFoundException(String message, Exception e) {
		super(message, e);
	}

}
