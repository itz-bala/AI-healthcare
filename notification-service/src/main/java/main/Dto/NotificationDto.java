package main.Dto;

import java.time.LocalDateTime;

import lombok.Data;
@Data
public class NotificationDto {
	
	
	
	    private Long patientId;
	    private String patientName;
	    private String patientEmail;
	    private String patientPhonenum;

	    private Long doctorId;
	    private String doctorName;
	    private String doctorEmail;
	    private String doctorPhonenum;

	    private LocalDateTime appointmentDate;

	  
	    
	    
	   

}
