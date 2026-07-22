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

import main.Dto.AppointmentAnalyticsDto;
import main.Dto.AppointmentRequestDto;
import main.Dto.AppointmentResponseDto;
import main.Entity.Department;
import main.Service.AppointmentService;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {
	
	
	private final AppointmentService  service;
	
	
	public AppointmentController(AppointmentService service) {
		
		this.service=service;
		
	}
	
	
	//register
	
	
	@PostMapping("/booking")
	
	public ResponseEntity<AppointmentResponseDto>createBooking(@RequestBody AppointmentRequestDto dto){
		
		AppointmentResponseDto appointment=service.createBooking(dto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(appointment);
		
	}
	
	//getbyid
	
	@GetMapping("/getById/{id}")
	public ResponseEntity<AppointmentResponseDto>getbyid(@PathVariable Long id){
		
		AppointmentResponseDto appointment=service.getbyid(id);
		
		return ResponseEntity.ok(appointment);
	}
	
	//getALL appointments
	
	@GetMapping("/getAll")
	public ResponseEntity<List<AppointmentResponseDto>>getAll(){
		
		List<AppointmentResponseDto> dto=service.getAll();
		
		return ResponseEntity.ok(dto);
	}

	
	//   Appointment analytics
	
	@GetMapping("/analytics")
	public ResponseEntity<AppointmentAnalyticsDto> analytics() {

	    AppointmentAnalyticsDto dto = service.analytics();

	    return ResponseEntity.ok(dto);
	}
	
	
	//count
	
	@GetMapping("/count")
	public ResponseEntity<Long>getAllAppointmentscount(){
		
		Long count=service.getAllAppointmentscount();
		
		return ResponseEntity.ok(count);
	}
	
	//today appointment  count
	
	@GetMapping("/todayCount")
	public ResponseEntity<Long> todayCount(){

	    Long count = service.todayCount();

	    return ResponseEntity.ok(count);
	}
	
	
	//count completed appointments
	
	@GetMapping("/completed")
	public ResponseEntity<Long>completedAppointments(){
		
		Long completed=service.completedAppointments();
		
		return ResponseEntity.ok(completed);
	}
	
	
	//cancelled appointments count
	
	@GetMapping("/cancelled")
	public ResponseEntity<Long>cancelledAppointments(){
		
		Long cancelled=service.cancelledAppointments();
		
		return ResponseEntity.ok(cancelled);
	}
	
	
	
	
	//update  appointment status to cancelled
	
	@PutMapping("/update/cancelled/{id}")
	public ResponseEntity<String>updateToCancelled(@PathVariable Long id){
		
		service.updateToCancelled( id);
		
		return ResponseEntity.ok("booking is cancelled");
	}
	
	//update appointment status to completed
	
	
	@PutMapping("/update/completed/{id}")
	public ResponseEntity<String>updateToCompleted(@PathVariable Long id){
		
		service.updateToCompleted(id);
		
		return ResponseEntity.ok("booking is completed");	
			
		}
	
	//appointment department analytics
	
	
	@GetMapping("/AppointmentDepartmentAnalytics")
	public ResponseEntity<Map<Department,Long>>getAppointmentDepartmentAnalytics(){
		
		Map<Department,Long>dto=service.getAppointmentDepartmentAnalytics();
		
		return ResponseEntity.ok(dto);
	}
	
	//appointment trends 
	
	@GetMapping("/appointment/trends/last7days")
	public ResponseEntity< Map<String, Long>> last7DaysAppointments() {
		
		Map<String,Long>dto=service.last7DaysAppointments();
	    return ResponseEntity.ok(dto);
	}
	
	
	//get patient appointments----------->
	
	@GetMapping("/patient/{patientId}")
	public ResponseEntity<List<AppointmentResponseDto>>getAllPatientAppointments(@PathVariable Long patientId){
		
		List<AppointmentResponseDto>dto=service.getAllPatientAppointments(patientId);
		
		return ResponseEntity.ok(dto);
	}
	
	
	
}
