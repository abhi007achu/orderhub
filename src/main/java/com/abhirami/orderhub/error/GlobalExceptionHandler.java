package com.abhirami.orderhub.error;

import java.io.ObjectInputStream.GetField;
import java.time.Instant;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.StreamingHttpOutputMessage.Body;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(NotFoundException.class)
	ResponseEntity<Map<String, Object>> notFound(NotFoundException ex){
		return body(HttpStatus.NOT_FOUND,ex.getMessage());
	}
	
	@ExceptionHandler(ConflictException.class)
	ResponseEntity<Map<String, Object>> conflict(ConflictException ex){
		return body(HttpStatus.NOT_FOUND, ex.getMessage());
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	ResponseEntity<Map<String, Object>> invalid(MethodArgumentNotValidException ex){
		String details = ex.getBindingResult().getFieldErrors().stream()
						.map(e -> e.getField() + ":" +e.getDefaultMessage())
						.collect(Collectors.joining("; "));
		return body(HttpStatus.BAD_REQUEST,details);
	}
	
	private ResponseEntity<Map<String, Object>> body(HttpStatus status, String message) {
		return ResponseEntity.status(status).body(Map.of(
				"tinestamp",Instant.now().toString(),
				"status",status.value(),
				"error",status.getReasonPhrase(),
				"message",message));
		
	}
}
