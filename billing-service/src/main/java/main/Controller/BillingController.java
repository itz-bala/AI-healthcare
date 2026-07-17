package main.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import main.Dto.BillingAnalyticsDto;
import main.Dto.BillingRequestDto;
import main.Dto.BillingResponseDto;
import main.Entity.Department;
import main.Service.BillingService;

@RestController
@RequestMapping("/billing")
public class BillingController {
	
	
	
	private final BillingService service;
	
	
	public BillingController(BillingService service) {
		
		this.service=service;
	}
	
	
	//create billing
	
	@PostMapping("/createBilling")
	public ResponseEntity<BillingResponseDto>createBilling(@Valid @RequestBody BillingRequestDto dto){
		
		
		BillingResponseDto response=service.createBilling(dto);
		
		
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
		
	}
	
	
	
	//get by id
	
	@GetMapping("/getById/{id}")
	public ResponseEntity<BillingResponseDto>getbyid(@PathVariable Long id){
		
		BillingResponseDto dto=service.getbyid(id);
		
		return ResponseEntity.ok(dto);
		
		
	}
	
	//getAll
	
	@GetMapping("/getAll")
	public ResponseEntity<List<BillingResponseDto>>getAll(){
		
		List<BillingResponseDto>dto=service.getAll();
		
		return ResponseEntity.ok(dto);
	}
	
	
	//billing Analytics
	
	@GetMapping("/analytics")
	public ResponseEntity<BillingAnalyticsDto> analytics(){
		
		BillingAnalyticsDto dto=service.getBillingAnalytics();

	    return ResponseEntity.ok(dto);

	}
	
	
	//billing department analytics
	
	@GetMapping("/BillingDepartmentAnalytics")
	public ResponseEntity<Map<Department,Long>>getBillingDepartmentAnalytics(){
		
		Map<Department,Long>dto=service.getBillingDepartmentAnalytics();
		
		return ResponseEntity.ok(dto);
	}
	

	  //billing trends
	
	@GetMapping("/billing/trends/last30days")
	public ResponseEntity< Map<String, Double>> last30DaysRevenue() {
		Map<String,Double>dto=service.last30DaysRevenue();
	    return ResponseEntity.ok(dto);
	}
	
	
	@GetMapping("/revenueByDepartment")
	public ResponseEntity<Map<String, Double>> getRevenueByDepartment() {
		
		Map<String,Double>dto=service.getRevenueByDepartment();

	    return ResponseEntity.ok(dto);

	}
	
}


