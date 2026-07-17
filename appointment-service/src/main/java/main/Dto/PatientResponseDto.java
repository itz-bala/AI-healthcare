package main.Dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class PatientResponseDto {
	
	
    private Long id;
	
	
	private String name;
	
	
	private String email;
	
	
	private String gender;
	
	private Integer age;
	
	
	private String phonenum;

	
	

	 private Boolean firstVisit;

	 private LocalDate registrationDate;
	

}
