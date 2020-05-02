package com.intellectdesign.igtb.lms.exception;

import java.util.List;

public class ValidationException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private List<ApiSubError> errorList;

	public ValidationException() {
		super();
	}

	public ValidationException(String message, Throwable cause) {
		super(message, cause);
	}

	public ValidationException(String message) {
		super(message);
	}

	public ValidationException(Throwable cause) {
		super(cause);
	}

	public List<ApiSubError> getErrorList() {
		return errorList;
	}

	public void setErrorList(List<ApiSubError> errorList) {
		this.errorList = errorList;
	}

	public ValidationException(List<ApiSubError> errorList) {
		super();
		this.errorList = errorList;
	}
	
	
	
}