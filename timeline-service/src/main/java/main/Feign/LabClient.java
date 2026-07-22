package main.Feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import main.Dto.LabResponseDto;

@FeignClient(name="lab-service",url="http://localhost:8077")
public interface LabClient {
	
	
	@GetMapping("lab/patient/{patientId}")
	List<LabResponseDto>getAllPatientLabReports(@PathVariable Long patientId);

}
