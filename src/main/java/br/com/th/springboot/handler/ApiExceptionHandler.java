package br.com.th.springboot.handler;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import br.com.th.springboot.dto.response.FieldError;

@ControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<?> handlerTypeMismatchException(MethodArgumentTypeMismatchException ex){
		
		FieldError error = new FieldError(ex.getParameter().getParameter().getName(), ex.getParameter().getParameter().getType().getName());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
		
	}
	
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<?> handlerDataIntegrityViolationException(DataIntegrityViolationException ex){
		
		String msg[] = ex.getMostSpecificCause().getMessage().split("\n");
		
		String error[] = msg[1].split("=");

		FieldError err = new FieldError(error[0], error[1]);
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
				
	}
	
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handlerMethodArgumentNotValidException(MethodArgumentNotValidException ex){
		
		List<FieldError> errors = ex.getFieldErrors()
										.stream()
										.map(e -> new FieldError(e.getField(), e.getDefaultMessage())).collect(Collectors.toList());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
				
	}
	
	
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<?> handlerRuntimeException(RuntimeException ex){

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
				
	}
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handlerException(Exception ex){

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
				
	}
	
	
	
}
