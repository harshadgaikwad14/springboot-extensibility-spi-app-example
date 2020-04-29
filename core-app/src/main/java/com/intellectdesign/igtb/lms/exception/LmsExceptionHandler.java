package com.intellectdesign.igtb.lms.exception;

import java.util.ArrayList;
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

import com.intellectdesign.igtb.lms.controller.TaxRestController;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class LmsExceptionHandler extends ResponseEntityExceptionHandler {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(LmsExceptionHandler.class);


	@ExceptionHandler(ValidationException.class)
	protected ResponseEntity<Object> handleEntityNotFound(ValidationException ex) {
		
		LOGGER.info("Exception Handling for Payload Validation");
		
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
		apiError.setMessage("Validation Error");
		apiError.setDebugMessage("Validation Failed");
		final String [] validationErrorString = ex.getMessage().split("~~");
		if(validationErrorString !=null )
		{
			List<ApiSubError> subErrors =new ArrayList<>();
			apiError.setSubErrors(subErrors );
			for (final String error : validationErrorString) {
				final ApiSubError apiSubError = new ApiValidationError(null, error);
				subErrors.add(apiSubError);
				
			}
			
		}
		return buildResponseEntity(apiError);
	}

	

	@ExceptionHandler(DataNotFoundException.class)
	protected ResponseEntity<Object> handleEntityNotFound(DataNotFoundException ex) {
		ApiError apiError = new ApiError(HttpStatus.NOT_FOUND);
		apiError.setMessage(ex.getMessage());
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