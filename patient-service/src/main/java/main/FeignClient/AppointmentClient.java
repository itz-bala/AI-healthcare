package main.FeignClient;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import main.Entity.Department;

@FeignClient(name="appointment-service",url="http://localhost:8072")
public interface AppointmentClient {
	
	@GetMapping("/appointment/AppointmentDepartmentAnalytics")
	Map<Department,Long>getAppointmentDepartment();

}
