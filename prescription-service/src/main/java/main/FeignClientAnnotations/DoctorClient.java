package main.FeignClientAnnotations;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import main.Dto.DoctorResponseDto;

@FeignClient(name="doctor-service",url="http://localhost:8074")
public interface DoctorClient {
	
	
	@GetMapping("/doctor/getById/{id}")
	DoctorResponseDto getDoctor(@PathVariable Long id);
	
	

}
