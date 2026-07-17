package main.Dto;

import java.time.LocalDateTime;

import lombok.Data;
import main.Entity.AppointmentStatus;


@Data
public class AppointmentResponseDto {

	
private Long id;
	
	
	private  Long  patientId;
	
	
	
	private Long doctorId;
	
	
	private AppointmentStatus appointmentStatus;
	
	
	
	private LocalDateTime  appointmentDate;


}
