package main.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import main.Dto.PatientAnalyticsDto;
import main.Dto.PatientRequestDto;
import main.Dto.PatientResponseDto;
import main.Entity.Department;
import main.Service.PatientService;



@RestController
@RequestMapping("/patient")
public class PatientController {
	
	
	private  final PatientService service;
	
	
	public PatientController(PatientService service) {
		
		
		this.service=service;
	}
	
	
	//posting
	
	
	@PostMapping("/register")
	public ResponseEntity<PatientResponseDto>createPatient( @Valid @RequestBody PatientRequestDto dto){
		
		PatientResponseDto patient=service.createPatient(dto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(patient);
	}

	
	
	//get by id
	
	@GetMapping("/getById/{id}")
	public ResponseEntity<PatientResponseDto>getbyid(@PathVariable Long id){
		
		PatientResponseDto dto=service.getbyid(id);
		
		
		return ResponseEntity.ok(dto);
	}
	
	//getAll
	
	@GetMapping("/getAll")
	public ResponseEntity< List<PatientResponseDto>>getAll(){
		
		List<PatientResponseDto> dto=service.getAll();
		
		return ResponseEntity.ok(dto);
		
	}
	
	
	
	//analytics
	
	@GetMapping("/analytics")
	public ResponseEntity<PatientAnalyticsDto> getAnalytics() {
		
	PatientAnalyticsDto dto=service.getAnalytics();
	    return ResponseEntity.ok(dto);
	}
	
	
	//count
	
	@GetMapping("/count")
	public ResponseEntity<Long> getPatientCount() {
		
		Long l=service.getPatientCount();

	    return ResponseEntity.ok(l);

	}
	
	
	//active count
	
	@GetMapping("/activeCount")
	public ResponseEntity<Long> getActivePatientCount() {
		
		Long l2=service.getActivePatientCount();

	    return ResponseEntity.ok(l2);

	}
	
	//inActive count
	
	@GetMapping("/inActiveCount")
	public ResponseEntity<Long>getInActivePatientsCount(){
		
		Long l3=service.getInActivePatientsCount();
		return ResponseEntity.ok(l3);
	}
	
	//update firstvisit..............>this goes to appointment-service through feignClinet
	
	@PutMapping("/update/firstVisit/{id}")
	
	public ResponseEntity<String>updateFirstVisit(@PathVariable Long id){
		
		service.updateFirstVisit(id);
		
		return ResponseEntity.ok("first visit is updated");
	}
	
	
	//update active to inactive
	
	@PutMapping("/update/activeToInactivePatients/{id}")
	public ResponseEntity<String>inactivePatient(@PathVariable Long id){ 
		
		service.inactivePatient(id);
		
		return ResponseEntity.ok("active to deactive patient is updated");
		
	}
	
	//update inactive to active
	
	@PutMapping("/update/inactiveToactivePatients/{id}")
	public ResponseEntity<String>activePatients(@PathVariable Long id){
		
		
		  service.activePatients(id);
		  
		  return ResponseEntity.ok("inactive to active patient is updated");
	}
	
	
	
	//patient trends
	
	
	@GetMapping("/patient/trends/monthlyGrowth")
	public ResponseEntity<Map<String, Long>> monthlyGrowth() {
		
		Map<String,Long>dto=service.monthlyGrowth();
	    return ResponseEntity.ok(dto);
	}
	
	//patient department analytics
	
	@GetMapping("/departmentAnalytics")
	public ResponseEntity<Map<Department,Long>>getDepartmentWisePatients(){
		
		Map<Department,Long>dto=service.getDepartmentWisePatients();
		return ResponseEntity.ok(dto);
	}
}
