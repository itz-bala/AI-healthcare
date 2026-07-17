package main.FeignClient;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import main.Dto.DoctorAnalyticsDto;

@FeignClient(name="doctor-service",url="http://localhost:8074")
public interface DoctorClient {
	
	@GetMapping("/doctor/analytics/{id}")
	DoctorAnalyticsDto getDoctorAnalytics(@PathVariable Long id);
	
	
	@GetMapping("/doctor/count")
	Long getTotalDoctors();
	
	@GetMapping("/doctor/activeCount")
	Long getActiveDoctor();
	
	
	@GetMapping("/doctor/doctorDepartmentAnalytics")
	Map<String, Long> getDoctorsByDepartment();

}
