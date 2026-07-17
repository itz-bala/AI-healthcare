package main.FeignClient;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import main.Dto.AppointmentAnalyticsDto;

@FeignClient(name="appointment-service",url="http://localhost:8072")
public interface AppointmentClient {
	
	@GetMapping("appointment/analytics")
	AppointmentAnalyticsDto getAppointmentAnalytics();
	
	
	@GetMapping("/appointment/count")
	Long getTotalAppointments();
	
	
	@GetMapping("/appointment/todayCount")
	Long getTodayAppointments();
	
	@GetMapping("/appointment/completed")
	Long getCompletedAppointments();
	
	@GetMapping("/appointment/cancelled")
	Long getCancelledAppointments();
	
	@GetMapping("/appointment/appointment/trends/last7days")
	Map<String, Long> last7DaysAppointments();
	
	@GetMapping("/appointment/AppointmentDepartmentAnalytics")
	Map<String, Long> getAppointmentsByDepartment();
	
	@GetMapping("/billing/analytics")
	Map<String, Double> getRevenueByDepartment();

}
