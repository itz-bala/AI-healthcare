package main.Dto;

import java.util.Map;

import lombok.Data;

@Data
public class TrendAnalyticsDto {
	
	
	
	private Map<String, Long> last7DaysAppointments;

    private Map<String, Double> last30DaysRevenue;

    private Map<String, Long> monthlyPatientGrowth;

    private Map<String, Double> overallHospitalPerformance;

}
