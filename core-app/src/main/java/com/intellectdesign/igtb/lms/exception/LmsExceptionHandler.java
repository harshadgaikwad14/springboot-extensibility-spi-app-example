package com.intellectdesign.igtb.lms.exception;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class LmsExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(LmsExceptionHandler.class);

	@ExceptionHandler(ValidationException.class)
	protected ResponseEntity<Object> handleEntityNotFound(ValidationException ex) {

		LOGGER.info("Exception Handling for Payload Validation");

		final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
		apiError.setMessage("Validation Failure");
		apiError.setDebugMessage("Entity Validation Failure");

		final List<ApiSubError> subErrors = ex.getErrorList();
		if (!subErrors.isEmpty()) {

			apiError.setSubErrors(subErrors);

		}
		return buildResponseEntity(apiError);
	}

	@ExceptionHandler(DataNotFoundException.class)
	protected ResponseEntity<Object> handleEntityNotFound(DataNotFoundException ex) {
		ApiError apiError = new ApiError(HttpStatus.NOT_FOUND);
		apiError.setMessage(ex.getMessage());
		return buildResponseEntity(apiError);
	}
	
	
	@ExceptionHandler(PersistanceException.class)
	protected ResponseEntity<Object> handleEntityNotFound(PersistanceException ex) {
		final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
		apiError.setMessage("Persistence Failure");
		apiError.setDebugMessage(ex.getMessage());
		return buildResponseEntity(apiError);
	}
	
	
	@ExceptionHandler(Exception.class)
	protected ResponseEntity<Object> handleEntityNotFound(Exception ex) {
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
		apiError.setMessage("System Error");
		apiError.setDebugMessage(ex.getMessage());
		return buildResponseEntity(apiError);
	}

	private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}

}