package main.FeignClient;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import main.Dto.BillingAnalyticsDto;

@FeignClient(name="billing-service",url="http://localhost:8075")
public interface BillingClient {
	
	@GetMapping("/billing/analytics")
	BillingAnalyticsDto getBillingAnalytics();
	
	@GetMapping("/billing/billing/trends/last30days")
	Map<String, Double> last30DaysRevenue();
	
	@GetMapping("/billing/revenueByDepartment")
	Map<String, Double> getRevenueByDepartment();
	
}
