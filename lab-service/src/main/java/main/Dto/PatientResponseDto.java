package main.Dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class PatientResponseDto {
	
	
	
	    private Long id;
		
		@NotBlank(message="name must be")
		private String name;
		
		@Email(message="invalid email")
		private String email;
		
		
		private String gender;
		
		private Integer age;
		
		
		private String phonenum;
		
		

	    private Boolean firstVisit;
	     
	    
	    private LocalDate registrationDate;



}
