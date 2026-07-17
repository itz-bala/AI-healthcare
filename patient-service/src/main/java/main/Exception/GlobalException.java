package main.Exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>>validation_handling(MethodArgumentNotValidException ex){
		
		
		Map<String,String>errors=new HashMap<>();
		
		ex.getBindingResult().getFieldErrors() 
		   .forEach(e->errors.put(e.getField(),e.getDefaultMessage()));
		
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	}
	
	
	
	@ExceptionHandler(EmailExistsException.class)
	public ResponseEntity<String>already_exists(EmailExistsException ex){
		
		
		return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
	}
	
	
	
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<String>not_found(IdNotFoundException ex){
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		
	}
	
	

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String>internal_error(Exception ex){
		
		 ex.printStackTrace();
		 
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.toString());
	}
	
	

}
