package main.FeignClientAnnotations;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import main.Dto.LabResponseDto;

@FeignClient(name="lab-service",url="http://localhost:8077")
public interface LabClient {
	
	@GetMapping("/lab/getById/{id}")
	LabResponseDto getLab(@PathVariable Long id);

}
