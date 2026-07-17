package main.Service;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;
import main.Dto.PatientAnalyticsDto;
import main.Dto.PatientRequestDto;
import main.Dto.PatientResponseDto;
import main.Entity.Department;
import main.Entity.Patient;
import main.Exception.EmailExistsException;
import main.Exception.IdNotFoundException;
import main.FeignClient.AppointmentClient;
import main.Mapper.MappingPlace;
import main.Repository.PatientRepository;

@Service

public class PatientService_2  implements PatientService{

	
	@Autowired
	private  PatientRepository repo;
	
	
	@Autowired
	private  MappingPlace mapper;
	
	@Autowired
	private AppointmentClient appointmentclient;

	
	//post

	@Override
	public PatientResponseDto createPatient(@Valid PatientRequestDto dto) {
		// TODO Auto-generated method stub
		
		
		if(repo.existsByEmail(dto.getEmail())) {
			
			throw new EmailExistsException("this email already exists please use new one");
		}
		
		
		Patient patient=mapper.toEntity(dto);
		
		   patient.setFirstVisit(true);
		   
		
		Patient patient_2=repo.save(patient);
		
		
		
		return mapper.toDto(patient_2);
	}


	@Override
	public PatientResponseDto getbyid(Long id) {
		// TODO Auto-generated method stub
		
		Patient patient=repo.findById(id) 
				  .orElseThrow(()->new IdNotFoundException("patient id is not found"));
				
		
		return mapper.toDto(patient);
	}


	
	//analytics
	
	
	
	
	@Override
	public PatientAnalyticsDto getAnalytics() {
		// TODO Auto-generated method stub
		
		
		
		PatientAnalyticsDto dto=new PatientAnalyticsDto();
		
		dto.setNewPatients(repo.countByFirstVisitTrue());
		
		dto.setReturningPatients(repo.countByFirstVisitFalse());
		
		dto.setMalePatients(repo.countByGender("Male"));
		
		dto.setFemalePatients(repo.countByGender("Female"));
		
		dto.setOtherPatients(repo.countByGender("Others"));
		
		dto.setChildren(repo.countByAgeLessThan(18));

	    dto.setAdults(repo.countByAgeBetween(18, 40));

	    dto.setMiddleAge(repo.countByAgeBetween(41, 60));

	    dto.setSeniorCitizens(repo.countByAgeGreaterThan(60));

	  
   
	    // Get department analytics from Appointment Service
	    
	    Map<Department, Long> departmentMap = appointmentclient.getAppointmentDepartment();

	    Department mostVisitedDepartment = departmentMap.entrySet()
	            .stream()
	            .max(Map.Entry.comparingByValue())
	            .map(Map.Entry::getKey)
	            .orElse(null);

	    dto.setMostVisitedDepartment(mostVisitedDepartment);

	    
	    
	    return dto;
		
		
		
	}

 
	
	//total counts
	@Override
	public Long getPatientCount() {
		// TODO Auto-generated method stub
		return repo.count();
	}


	
	//active patients count
	@Override
	public Long getActivePatientCount() {
		// TODO Auto-generated method stub
		return repo.countByActiveTrue();
	}


	
	
	
	//inactive patients count
	@Override
	public Long getInActivePatientsCount() {
		// TODO Auto-generated method stub
		return repo.countByActiveFalse();
	}


	
	//update first visit
	@Override
	public void updateFirstVisit(Long id) {
		// TODO Auto-generated method stub
		
	Patient patient=repo.findById(id)	 
			 .orElseThrow(()->new RuntimeException("id is not found"));
	
	patient.setFirstVisit(false);
		
		 repo.save(patient);
	}


	
	//getALL patients
	
	@Override
	public List<PatientResponseDto> getAll() {
		// TODO Auto-generated method stub
		
		List<Patient>patientlist=repo.findAll();
		
		return patientlist.stream().map(mapper::toDto).toList();
	}


	
	  //update active to inactive
	
	@Override
	public void inactivePatient(Long id) {
		// TODO Auto-generated method stub
		
		Patient patient=repo.findById(id)  
				        .orElseThrow(()->new RuntimeException("id is not found"));
		
		patient.setActive(false);
		
		repo.save(patient);
	}

      
	
	//update inactive to active
	@Override
	public void activePatients(Long id) {
		// TODO Auto-generated method stub
		
		Patient patient=repo.findById(id)  
		        .orElseThrow(()->new RuntimeException("id is not found"));

     patient.setActive(true);

      repo.save(patient);
		
	}



	
	//patient trends
	
	@Override
	public Map<String, Long> monthlyGrowth() {
		// TODO Auto-generated method stub
		
		List<Patient>patientlist=repo.findAll();
		
		
		return patientlist.stream().collect(Collectors.groupingBy(p->p.getRegistrationDate().getMonth().toString(),
				              TreeMap::new,Collectors.counting()));
	}
	
	

	//patient department analytics    //each department how many patients visited 
	
	@Override
	public Map<Department, Long> getDepartmentWisePatients(){
		// TODO Auto-generated method stub
		return appointmentclient.getAppointmentDepartment();
	}


	
	
	
	
	
	

	
	
	
	
	
}
