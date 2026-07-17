package main.Dto;

import java.time.LocalTime;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import main.Entity.Department;

@Data
public class DoctorResponseDto {
	
	 private  Long id;
		
		
		private String name;
		
		
		
		private Department department;


		
		private String email;
		
		 
	     private Double consultationFees;
	     
	     private String phonenum;
		
	     private Integer reviewRating;

	     private LocalTime consultationStartTime;

	     private LocalTime consultationEndTime;


}
