package main.Entity;

import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name="doctor")
@Data
public class Doctor {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private  Long id;
	
	
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
 	
 	private boolean active=true;
 	
 	
 	@Min(1)
 	@Max(5)
 	private Integer reviewRating;
 	
 	
 	private LocalTime   consultationStartTime;
 	
 	private LocalTime  consultationEndTime;




}
