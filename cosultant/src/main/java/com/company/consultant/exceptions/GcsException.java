package com.company.consultant.exceptions;

public class GcsException extends Exception {

	public int code = 501;
	
	public GcsException(String message, int code) {
		super(message);
		this.code = code;
	}
}
