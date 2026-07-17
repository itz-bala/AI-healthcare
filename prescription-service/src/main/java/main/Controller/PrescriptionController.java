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
import main.Dto.PrescriptionAnalyticsDto;
import main.Dto.PrescriptionRequestDto;
import main.Dto.PrescriptionResponseDto;
import main.Service.PrescriptionService;

@RestController
@RequestMapping("/prescription")
public class PrescriptionController {
	
	
	
	@Autowired
	private PrescriptionService service;
	
	
	//posting
	
	@PostMapping("/createPrescription")
	public ResponseEntity<PrescriptionResponseDto>createPrescription(@Valid @RequestBody PrescriptionRequestDto dto){
		
		
		PrescriptionResponseDto response=service.createPrescription(dto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	
	//get by id
	
	@GetMapping("/getById/{id}")
	
	public ResponseEntity<PrescriptionResponseDto>getbyid(@PathVariable Long id){
		
		
		PrescriptionResponseDto response=service.getbyid(id);
		
		return ResponseEntity.ok(response);
	}
	

	
	//getAll
	
	@GetMapping("/getAll")
	
	public ResponseEntity<List<PrescriptionResponseDto>>getall(){
		
		List<PrescriptionResponseDto> dto=service.getall();
		
		return ResponseEntity.ok(dto);
	}
	
	//analytics
	
	@GetMapping("/analytics")
	public ResponseEntity<PrescriptionAnalyticsDto>analytics(){
		
		PrescriptionAnalyticsDto dto=service.analytics();
		
		return ResponseEntity.ok(dto);
	}
	
	
}
