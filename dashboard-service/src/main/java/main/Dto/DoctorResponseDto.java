package main.Dto;

import java.time.LocalTime;

import lombok.Data;
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
