package main.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import jakarta.validation.Valid;
import main.Dto.AppointmentResponseDto;
import main.Dto.DoctorResponseDto;
import main.Dto.LabAnalyticsDto;
import main.Dto.LabRequestDto;
import main.Dto.LabResponseDto;
import main.Dto.PatientResponseDto;
import main.Entity.Lab;
import main.Entity.LabStatus;
import main.Exception.ResourceNotFoundException;
import main.FeignClient.AppointmentClient;
import main.FeignClient.DoctorClient;
import main.FeignClient.PatientClient;
import main.Mapper.MappingPlace;
import main.Repository.LabRepository;

@Service
public class LabService_2 implements LabService{
	
	
	
	@Autowired
	private LabRepository repo;
	
	@Autowired
	private MappingPlace mapper;
	
//	@Autowired
//	private RestTemplate restTemplate;
	
	
	@Autowired
	private PatientClient patientclient;
	
	@Autowired
	private DoctorClient doctorclient;
	
	@Autowired
	private AppointmentClient appointmentclient;

	@Override
	public LabResponseDto createLabTest(@Valid LabRequestDto dto) {
		// TODO Auto-generated method stub
		
		

		PatientResponseDto patient;
		DoctorResponseDto doctor;
		AppointmentResponseDto appointment;
		
		
		
		
		//get patient details
		try {
			
			
			patient=patientclient.getPatient(dto.getPatientId());
		}catch(HttpClientErrorException e) {
			
			
			throw new  ResourceNotFoundException("patient is not exists");
		}
		
		
		//.....................................
		
		
		//get doctor details
		
		try {
			
		doctor=doctorclient.getDoctor(dto.getDoctorId());
			
		
		}catch(HttpClientErrorException e) {
			
			
			throw new ResourceNotFoundException("doctor is not exists");
		}
		
		//..............................................
		
		
		//get appointment details
		
		
		try {
			
			appointment=appointmentclient.getAppointment(dto.getAppointmentId());
			
		}catch(HttpClientErrorException e) {
			
			throw new ResourceNotFoundException("appointment is not exists");
		}
		
		
		//dto to entity convert
		
		Lab lab=mapper.toEntity(dto);
		
		
		lab=repo.save(lab);
		
		
		
		return mapper.toDto(lab);
	}

	@Override
	public LabResponseDto getbyid(Long id) {
		// TODO Auto-generated method stub
		
		Lab lab=repo.findById(id) 
				.orElseThrow(()->new ResourceNotFoundException("id is not exists in database"));
		
		
		return mapper.toDto(lab);
	}

	
//getAll
	
	@Override
	public List<LabResponseDto> getAll() {
		// TODO Auto-generated method stub
		
		List<Lab>lablist=repo.findAll();
		
		
		
		return lablist.stream().map(mapper::toDto).toList();
	}

	
	
	//analytics
	
	
	@Override
	public LabAnalyticsDto getAnalytics() {
		// TODO Auto-generated method stub
		
		LabAnalyticsDto dto=new LabAnalyticsDto();
		
		
		//total tests
		Long totalTests=repo.count();
		
		dto.setTotalLabTests(totalTests);
		
		
		//completed tests
		
	Long completed=	repo.countByLabStatus(LabStatus.COMPLETED);
	
	dto.setCompletedReports(completed);
		
		
	//pending tests
	
	Long pending=repo.countByLabStatus(LabStatus.PENDING);
	
	dto.setPendingReports(pending);
	
	
	//cancelled tests
	
	Long cancelled=repo.countByLabStatus(LabStatus.CANCELLED);
	
	
	
	
	
	
	
	
	
	
	// request labtests  and most requested labtests
	
	List<Lab>lablist=repo.findAll();
	
	
	Map<String,Long>maplist=lablist.stream().collect(Collectors.groupingBy(
			                     
			                     Lab::getTestName,Collectors.counting() ));
	
	
	dto.setRequestedLabTests(maplist);
	
	
	
	Long max=maplist.values().stream().max(Long::compareTo).orElse(0L);
	
	
	Map<String,Long>mostrequested=maplist.entrySet().stream() 
			       
			            .filter(e->e.getValue()==max)
			            .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));
	
	
	dto.setMostRequestedLabTests(mostrequested);
	
		return dto;
	}

	
	
	
	
	
	
	
	
	
	
	
	

}
