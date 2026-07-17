package main.FeignClientAnnotations;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import main.Dto.PrescriptionResponseDto;

@FeignClient(name="prescription-service",url="http://localhost:8076")
public interface PrescriptionClient {
	
	@GetMapping("/prescription/getById/{id}")
	
	PrescriptionResponseDto getPrescription(@PathVariable Long id);

}
