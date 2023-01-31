package org.aggregation.error;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(CommonException.class)
	public ResponseEntity<Object> handleCommonException(CommonException ex){
		ApiError apiError = new ApiError();
		apiError.setHttpStatus(HttpStatus.BAD_REQUEST);
		apiError.setMessage(ex.getMessage());
		apiError.setLocalDateTime(LocalDateTime.now());
		return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
		
	}

}
