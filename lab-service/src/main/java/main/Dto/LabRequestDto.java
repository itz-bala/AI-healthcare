package main.Dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import main.Entity.LabStatus;
import main.Entity.Department;

@Data
public class LabRequestDto {
	
	
	
	@NotNull(message="patient id is must be")
	private  Long patientId;
	
	@NotNull(message="doctor id is must be")
	private Long  doctorId;
	
	@NotNull(message="appointment id is must be")
	private Long appointmentId;
	
	
	private String testName;
	
	@NotNull(message="labfees must be")
	private Double labFees;
	
	
	@NotNull(message="lab status must be")
	@Enumerated(EnumType.STRING)
	private  LabStatus labStatus;
	
	private String result;

}
