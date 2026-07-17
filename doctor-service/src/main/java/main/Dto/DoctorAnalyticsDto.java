package main.Dto;

import java.util.stream.Stream;

import lombok.Data;

@Data
public class DoctorAnalyticsDto {
	
	
	
	private long totalAppointments;

    private long completedAppointments;

    private long cancelledAppointments;
    
    private long pendingAppointments;

    private long patientsTreated;

    private double averageRating;

    private String consultationHours;

	

}
