package main.FeignClients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import main.Dto.AppointmentResponseDto;

@FeignClient(name="appointment-service",url="http://localhost:8072")
public interface AppointmentClient {
	
	@GetMapping("/appointment/getAll")
	
	List<AppointmentResponseDto>getAllAppointments();

}
