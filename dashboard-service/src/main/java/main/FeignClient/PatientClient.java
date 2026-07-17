package main.FeignClient;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import main.Dto.PatientAnalyticsDto;

@FeignClient(name="patient-service",url="http://localhost:8071")
public interface PatientClient {
	
	@GetMapping("/patient/analytics")
	
	PatientAnalyticsDto  getPatientAnalytics();
	
	
	@GetMapping("/patient/count")
	Long getTotalPatients();
	
	@GetMapping("/patient/activeCount")
	Long getActivePatients();
	
	@GetMapping("/patient/patient/trends/monthlyGrowth")
	Map<String, Long> monthlyGrowth();
	
	
	@GetMapping("/patient/departmentAnalytics")
	Map<String, Long> getPatientsByDepartment();

}
