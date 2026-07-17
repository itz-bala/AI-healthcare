package main.Dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import main.Entity.Department;

@Data
public class AppointmentRequestDto {
	
	
	@NotNull(message="patient id is ,must be")
	private  Long  patientId;
	
	
	@NotNull(message="doctor id must be")
	private Long doctorId;
	
	private Department department;
	
	@NotNull(message="appointment date and time is m ust be")
	private LocalDateTime  appointmentDate;

}
