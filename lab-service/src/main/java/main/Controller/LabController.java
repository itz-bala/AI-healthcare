package main.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import main.Dto.LabAnalyticsDto;
import main.Dto.LabRequestDto;
import main.Dto.LabResponseDto;
import main.Service.LabService;

@RestController
@RequestMapping("/lab")
public class LabController {
	
	
	@Autowired
	private LabService service;
	
	

	
	
	//post
	
	@PostMapping("/createLabTest")
	public ResponseEntity<LabResponseDto>createLabTest(@Valid @RequestBody LabRequestDto dto){
		
		
		LabResponseDto response=service.createLabTest(dto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	
	//get by id
	
	@GetMapping("/getById/{id}")
	public ResponseEntity<LabResponseDto>getbyid(@PathVariable Long id){
		
		LabResponseDto response=service.getbyid(id);
		
		return ResponseEntity.ok(response);
	}
	
	
	//getAll
	
	@GetMapping("/getAll")
	public ResponseEntity<List<LabResponseDto>>getAll(){
		
		
		List<LabResponseDto>dto=service.getAll();
		
		return ResponseEntity.ok(dto);
		
	}
	
	
	//analytics
	
	@GetMapping("/analytics")
	public ResponseEntity<LabAnalyticsDto>getAnalytics(){
		
		LabAnalyticsDto dto=service.getAnalytics();
		
		return ResponseEntity.ok(dto);
	}
	
	
	//get patient lab details
	
	@GetMapping("/patient/{patientId}")
	
	public ResponseEntity<List<LabResponseDto>>getAllPatientLabReports(@PathVariable Long patientId){
		
		List<LabResponseDto>dto=service.getAllPatientLabReports(patientId);
		
		return ResponseEntity.ok(dto);
		
		
	}
	
	
}
