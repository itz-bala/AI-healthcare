package main.Dto;

import java.util.Map;

import lombok.Data;

@Data
public class DepartmentAnalyticsDto {
	
	
	private Map<String, Long> doctorsCount;

    private Map<String, Long> patientsCount;

    private Map<String, Long> appointmentsCount;

    private Map<String, Double> revenueGenerated;

}
