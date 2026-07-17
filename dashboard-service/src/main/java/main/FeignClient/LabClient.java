package main.FeignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import main.Dto.LabAnalyticsDto;

@FeignClient(name="lab-service",url="http://localhost:8077")
public interface LabClient {
	
	@GetMapping("/lab/analytics")
	LabAnalyticsDto getLabAnalytics();

}
