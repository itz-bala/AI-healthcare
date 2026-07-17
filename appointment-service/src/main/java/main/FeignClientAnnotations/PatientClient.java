package main.FeignClientAnnotations;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import main.Dto.PatientResponseDto;

@FeignClient(name="patient-service",url="http://localhost:8071")
public interface PatientClient {
	
	@GetMapping("/patient/getById/{id}")
	
	PatientResponseDto getPatient(@PathVariable Long id );
	
	
	@PutMapping("/patient/update/firstVisit/{id}")
	 void updateFirstVisist(@PathVariable Long id);
    
}
