package main.Dto;

import lombok.Data;

@Data
public class DoctorAnalyticsDto {
	
	 private long totalAppointments;

	    private long completedAppointments;

	    private long cancelledAppointments;

	    private long patientsTreated;

	    private double averageRating;

	    private String consultationHours;

}
