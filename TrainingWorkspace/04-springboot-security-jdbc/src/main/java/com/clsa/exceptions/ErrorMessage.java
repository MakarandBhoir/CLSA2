package com.clsa.exceptions;

public class ErrorMessage {
	private String url; 
	private String message;
	private int statusCode;
	

	public ErrorMessage(String url, String message) {
		this.url = url;
		this.message = message;
	}
	
	public ErrorMessage(String url, String message, int statusCode) {
		super();
		this.url = url;
		this.message = message;
		this.statusCode = statusCode;
	}

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
}
