package main.Dto;

import java.time.LocalTime;

import lombok.Data;
import main.Enum.Department;




@Data
public class DoctorService {
	

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
