package main.Dto;

import java.util.Map;

import lombok.Data;

@Data
public class AppointmentAnalyticsDto {
	
	
	private long dailyAppointments;

    private long weeklyAppointments;

    private long monthlyAppointments;

    private Map<String, Long> appointmentsByDepartment;

    private Map<String, Long> appointmentsByDoctor;

    private Map<String, Long> peakBookingHours;

    private double averageConsultationTime;

}
