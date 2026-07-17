package main.Dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AppointmentResponseDto {
	
	
	    private Long id;
		
		
		private  Long  patientId;
		
		
		
		private Long doctorId;
		
		
		
		
		private LocalDateTime  appointmentDate;
		
		private LocalDateTime bookingTime;

}
