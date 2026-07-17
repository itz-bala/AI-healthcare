package main.Dto;

import java.time.LocalTime;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import main.Entity.Department;

@Data
public class DoctorRequestDto {
	
	@NotBlank(message="name must be")
     private String name;
	
	
	@NotNull(message="must be specialist")
	@Enumerated(EnumType.STRING)
	private Department department;

     @Email(message="invalid email")
	private String email;
     
     
     @NotNull(message="consultation fees must be")
     private Double consultationFees;
     
     
     
 	private String phonenum;
 	
 	
 	@Min(1)
 	@Max(5)
 	private Integer reviewRating;

 	private LocalTime consultationStartTime;

 	private LocalTime consultationEndTime;


}
