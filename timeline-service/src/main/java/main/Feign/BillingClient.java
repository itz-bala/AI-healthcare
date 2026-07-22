package main.Feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import main.Dto.BillingResponseDto;

@FeignClient(name="billing-service",url="http://localhost:8075")
public interface BillingClient {
	
	@GetMapping("/billing/patient/{patientId}")
	List<BillingResponseDto>getAllPatientBillings(@PathVariable Long patientId);

}
