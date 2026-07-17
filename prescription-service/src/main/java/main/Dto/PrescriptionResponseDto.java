package main.Dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class PrescriptionResponseDto {
	
	

	private Long id;
	
	private Long patientId;
	
	
	private Long doctorId;
	
	
	private Long appointmentId;
	
	
	private String diagnosis;
	
	
	private String symptoms;
	
	private String notes;
	
	
	private LocalDateTime prescriptionDate;
	
	
	private LocalDateTime  createdTime;
	
	
	private LocalDateTime updatedTime;
	
	List<PrescriptionMedicineResponseDto>medicines;
	

}
