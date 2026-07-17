package main.FeignClientAnnotations;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import main.Dto.AppointmentResponseDto;

@FeignClient(name="appointment-service",url="http://localhost:8072")
public interface AppointmentClient {
	
	
	@GetMapping("/appointment/getById/{id}")
	
	AppointmentResponseDto getAppointment(@PathVariable Long id);

}
