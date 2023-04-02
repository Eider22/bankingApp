package com.softlond.bankingApp.services;

public class ServiceCustomResponse {
	private String message;
	public ServiceCustomResponse(String message) {
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
		
}
