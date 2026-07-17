package main.FeignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import main.Dto.PrescriptionAnalyticsDto;

@FeignClient(name="prescription-service",url="http://localhost:8076")
public interface PrescriptionClient {
	
	
	@GetMapping("/prescription/analytics")
	PrescriptionAnalyticsDto getPrescriptionAnalytics();

}
