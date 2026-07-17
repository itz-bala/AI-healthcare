package main.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;
import main.Dto.AppointmentResponseDto;
import main.Dto.DoctorResponseDto;
import main.Dto.PatientResponseDto;
import main.Dto.PrescriptionAnalyticsDto;
import main.Dto.PrescriptionMedicineResponseDto;
import main.Dto.PrescriptionRequestDto;
import main.Dto.PrescriptionResponseDto;
import main.Entity.Prescription;
import main.Entity.PrescriptionMedicine;
import main.Exception.ResourceNotFoundException;
import main.FeignClientAnnotations.AppointmentClient;
import main.FeignClientAnnotations.DoctorClient;
import main.FeignClientAnnotations.PatientClient;
//import main.Entity.PrescriptionMedicine;
import main.Mapper.MappingPlace;
import main.Repository.PrescriptionRepository;

@Service
public class PrescriptionService_2 implements PrescriptionService{
	
	
	
	@Autowired
	private PrescriptionRepository repo;
	
	
	@Autowired
	private MappingPlace mapper;
	
	
	//@Autowired
	//private RestTemplate restTemplate;

	@Autowired
	private PatientClient patientclient;
	
	
	@Autowired
	private DoctorClient doctorclient;
	
	@Autowired
	private AppointmentClient appointmentclient;

	
	//post
	
	
	
	@Override
	public PrescriptionResponseDto createPrescription(@Valid PrescriptionRequestDto dto) {
		// TODO Auto-generated method stub
		
		
	
		
		PatientResponseDto patient;
		DoctorResponseDto doctor;
		AppointmentResponseDto appointment;
		
		
		//patient details
		try {
			
			patient=patientclient.getPatient(dto.getPatientId());
			
			
		}catch(Exception e) {
			
			throw new ResourceNotFoundException("patient is not exists");
		}
		
		
		//doctor details
		
		try {
			
			doctor=doctorclient.getDoctor(dto.getDoctorId());
			
		}catch(Exception e) {
			
			throw new ResourceNotFoundException("doctor is not exists");
		}
		
		
		//appointment details
		
		try {
			
			
			appointment=appointmentclient.getAppointment(dto.getAppointmentId());
			
		}catch(Exception e) {
			
			throw new ResourceNotFoundException("appointment is not exists");
		}
		
		
		
		
		
		//dto to entity convert
		
		Prescription prescription=mapper.toEntity(dto);
		
		
		
		List<PrescriptionMedicine>medi=dto.getMedicines().stream()
				                          .map(mapper::medicine_toEntity)
				                          .toList();
		
		
		//birectional mapping
		
		medi.forEach(m->m.setPrescription(prescription));
		prescription.setMedicines(medi);
		
		
		//everythibg stored in database
		Prescription savedPrescription=repo.save(prescription);
		
		
		
		//entity to dto convert
		
		PrescriptionResponseDto response=mapper.toDto(savedPrescription);
		
		
		List<PrescriptionMedicineResponseDto>mediResponse=savedPrescription.getMedicines().stream() 
				                                           .map(mapper::medicine_toDto).toList();             
		
		
		response.setMedicines(mediResponse);
		
		return response;
	}



	@Override
	public PrescriptionResponseDto getbyid(Long id) {
		// TODO Auto-generated method stub
		
		
		Prescription prescription=repo.findById(id) 
				                   .orElseThrow(()->new ResourceNotFoundException("prescription id is not found"));
		
		//prescription entity to dto convert
		PrescriptionResponseDto response=mapper.toDto(prescription);
		
		List<PrescriptionMedicineResponseDto>mediresponse=prescription.getMedicines() 
				                                        .stream().map(mapper::medicine_toDto).toList();
		
		response.setMedicines(mediresponse);
		
		return response;
	}

//getAll
	

	@Override
	public List<PrescriptionResponseDto> getall() {
		// TODO Auto-generated method stub
		
		 List<Prescription> prescriptionList = repo.findAll();

		    return prescriptionList.stream()
		            .map(p -> {

		                // Entity -> DTO
		                PrescriptionResponseDto response = mapper.toDto(p);

		                // Medicines Entity -> DTO
		                List<PrescriptionMedicineResponseDto> medicineResponse =
		                        p.getMedicines()
		                                .stream()
		                                .map(mapper::medicine_toDto)
		                                .toList();

		                response.setMedicines(medicineResponse);

		                return response;
		            })
		            .toList();
	}


	
	
	//analytics
	
	

	@Override
	public PrescriptionAnalyticsDto analytics() {
		// TODO Auto-generated method stub
		List<Prescription> prescriptions = repo.findAll();


	    PrescriptionAnalyticsDto dto = new PrescriptionAnalyticsDto();


	    // total prescriptions

	    dto.setTotalPrescriptions(
	            prescriptions.size()
	    );



	    // most prescribed medicines

	    Map<String, Long> medicineMap =
	            prescriptions.stream()
	                    .flatMap(p -> p.getMedicines().stream())
	                    .collect(Collectors.groupingBy(
	                            PrescriptionMedicine::getMedicineName,
	                            Collectors.counting()
	                    ));


    dto.setMostPrescribedMedicines(medicineMap);



	    // prescriptions by doctor

	    Map<String, Long> doctorMap =
	            prescriptions.stream()
	                    .collect(Collectors.groupingBy(
	                            p -> String.valueOf(p.getDoctorId()),
	                            Collectors.counting()
	                    ));


	    dto.setPrescriptionsByDoctor(doctorMap);



	    // prescriptions by department
	    Map<String, Long> departmentMap = new HashMap<>();


	    for(Prescription p : prescriptions) {


	        DoctorResponseDto doctor=doctorclient.getDoctor(p.getDoctorId());


	        String department =
	                doctor.getDepartment().toString();


	        departmentMap.put(
	                department,
	                departmentMap.getOrDefault(department, 0L) + 1
	        );

	    }


	    dto.setPrescriptionsByDepartment(departmentMap);

	    return dto;
	}
		
	
	
	
	
	

}
