package com.clsa.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ResponseBody
	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = {StudentNotFoundException.class, DuplicateStudentException.class})
    protected ErrorMessage handleConflict(Exception ex, HttpServletRequest request) {
		ex.printStackTrace();
		String errorMsg = ex.getMessage();
		String uri = request.getRequestURL().toString();
		return new ErrorMessage(uri, errorMsg, 500);
    }
}
