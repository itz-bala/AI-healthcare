package main.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
import main.Dto.DoctorAnalyticsDto;
import main.Dto.DoctorRequestDto;
import main.Dto.DoctorResponseDto;
import main.Entity.Department;
import main.Service.DoctorService;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
	
	
	@Autowired
	private DoctorService service;
	
	//register
	
	
	@PostMapping("/register")
	public ResponseEntity<DoctorResponseDto>createDoctor(@Valid @RequestBody DoctorRequestDto dto){
		
		DoctorResponseDto doctor=service.createDoctor(dto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(doctor);
	}
	
	
	//get by id
	
	
	@GetMapping("/getById/{id}")
	public ResponseEntity<DoctorResponseDto>getbyid(@PathVariable Long id){
		
		DoctorResponseDto doctor=service.getbyid(id);
		
		return ResponseEntity.ok(doctor);
		
	}
	
	//getAll
	
	@GetMapping("/getAll")
	public ResponseEntity<List<DoctorResponseDto>>getAll(){
		
		
		List<DoctorResponseDto>dto=service.getAll();
		
		return ResponseEntity.ok(dto);
	}
	
	
	//get specific department all doctors through specialist variable 
	
	@GetMapping("/getSpecialist/{department}")
	public ResponseEntity<List<DoctorResponseDto>>getSpecialists(@PathVariable Department department){
		
		
		List<DoctorResponseDto> dto=service.getSpecialists(department);
		
		
		return ResponseEntity.ok(dto);
	}
	
	
	
	
	// Total Doctors
	@GetMapping("/count")
	public ResponseEntity<Long> countDoctors() {

	    Long count = service.countDoctors();

	    return ResponseEntity.ok(count);
	}

	// Active Doctors count
	@GetMapping("/activeCount")
	public ResponseEntity<Long> getActiveDoctorsCount() {

	    Long count = service.getActiveDoctorsCount();

	    return ResponseEntity.ok(count);
	}
	
	//inactive doctors count
	
	@GetMapping("/inactiveCount")
	public ResponseEntity<Long>getInactiveDoctorsCount(){
		
		Long count=service.getInactiveDoctorsCount();
		
		return ResponseEntity.ok(count);
	}
	
	
	//update active to inactive
	
	@PutMapping("/updateToInactive/{id}")
	public ResponseEntity<String>updateActiveToInactive(@PathVariable Long id){
		
		   service.updateActiveToInactive(id);
		   
		   return ResponseEntity.ok(" active to inactive doctor is updated");
		
	}
	
	//update inactive to active
	
	@PutMapping("/update/inactiveToActive/{id}")
	public ResponseEntity<String>updateInactiveToActive(@PathVariable Long id){
		
		
		    service.updateInactiveToActive(id);
		    
		    return ResponseEntity.ok("inactive to active doctor is updated");
	}
	
	
	
	//doctor analytics
	
	@GetMapping("/analytics/{id}")
	public ResponseEntity<DoctorAnalyticsDto> doctorAnalytics(
	        @PathVariable Long id) {
		
		DoctorAnalyticsDto dto=service.doctorAnalytics(id);

	    return ResponseEntity.ok(dto );
	}
	
	//doctor department analysis
	
	@GetMapping("/doctorDepartmentAnalytics")
	public ResponseEntity<Map<Department,Long>>getDoctorDepartmentAnalytics(){
		
		
		Map<Department,Long>dto=service.getDoctorDepartmentAnalytics();
		
		return ResponseEntity.ok(dto);
	}
	
	
	

}
