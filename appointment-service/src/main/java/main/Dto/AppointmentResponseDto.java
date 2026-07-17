package main.Dto;

import java.time.LocalDateTime;

import lombok.Data;
import main.Entity.AppointmentStatus;
import main.Entity.Department;
@Data
public class AppointmentResponseDto {
	
	
    private Long id;
	
	
	private  Long  patientId;
	
	
	
	private Long doctorId;
	
	
	private AppointmentStatus appointmentStatus;
	
	private Department department;
	
	
	
	private LocalDateTime  appointmentDate;
	
	
	private LocalDateTime bookingTime;



	

	
	


}
