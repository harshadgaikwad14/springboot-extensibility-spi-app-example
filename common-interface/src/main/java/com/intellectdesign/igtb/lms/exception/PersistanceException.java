package com.intellectdesign.igtb.lms.exception;

public class PersistanceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PersistanceException() {
		super();
	}

	public PersistanceException(String message, Throwable cause) {
		super(message, cause);
	}

	public PersistanceException(String message) {
		super(message);
	}

	public PersistanceException(Throwable cause) {
		super(cause);
	}

}