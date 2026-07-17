package main.Dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;
import main.Entity.LabStatus;
@Data
public class LabResponseDto {
	
	
	
    private Long id;
	
	
	private  Long patientId;
	
	
	private Long  doctorId;
	
	
	private Long appointmentId;
	
	
	private String testName;
	
	
	
	
	
	private  LabStatus labStatus;
	
	private String result;
	
	
	private Double labFees;
	
	private LocalDate testDate;

	private LocalDate reportDate;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;


}
