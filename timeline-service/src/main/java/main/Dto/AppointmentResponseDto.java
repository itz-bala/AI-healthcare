package main.Dto;

import java.time.LocalDateTime;

import lombok.Data;
import main.Enum.AppointmentStatus;
import main.Enum.Department;



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
