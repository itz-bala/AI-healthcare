package main.Dto;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PrescriptionRequestDto {

	
	
   
	
	@NotNull(message="patient id is must be")
	private Long patientId;
	
	@NotNull(message="doctor id is must be")
	private Long doctorId;
	
	@NotNull(message="appointment id is must be")
	private Long appointmentId;
	
	@NotBlank(message="diagnosis must be")
	private String diagnosis;
	
	@NotBlank(message="symptoms must be")
	private String symptoms;
	
	private String notes;
	
	@NotEmpty(message="atlist one medicine need")
	@Valid
	List<PrescriptionMedicineRequestDto>medicines;
}
