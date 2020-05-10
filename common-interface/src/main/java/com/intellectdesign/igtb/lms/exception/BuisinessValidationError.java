package com.intellectdesign.igtb.lms.exception;

public class BuisinessValidationError extends ApiSubError {
	private String object;
	private String field;
	private String message;

	public BuisinessValidationError() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BuisinessValidationError(String object, String message) {
		super();
		this.object = object;
		this.message = message;
	}
	
	public BuisinessValidationError(String object, String field, String message) {
		super();
		this.object = object;
		this.field = field;
		this.message = message;
	}

	public String getObject() {
		return object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}