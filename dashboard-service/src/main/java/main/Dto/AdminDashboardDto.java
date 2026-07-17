package main.Dto;

import lombok.Data;

@Data
public class AdminDashboardDto {
	
	
	private Long totalPatients;
    private Long activePatients;

    private Long totalDoctors;
    private Long activeDoctors;

    private Long totalAppointments;
    private Long todayAppointments;
    private Long completedAppointments;
    private Long cancelledAppointments;

    private Double totalRevenue;
    private Double pendingPayments;

}
