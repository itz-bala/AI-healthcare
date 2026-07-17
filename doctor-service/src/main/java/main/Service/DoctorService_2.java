package main.Service;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;
import main.Dto.AppointmentResponseDto;
import main.Dto.DoctorAnalyticsDto;
import main.Dto.DoctorRequestDto;
import main.Dto.DoctorResponseDto;
import main.Entity.AppointmentStatus;
import main.Entity.Department;
import main.Entity.Doctor;
import main.Exception.EmailExistsException;
import main.Exception.IdNotFoundException;
import main.FeignClients.AppointmentClient;
import main.MAPPER.MappingPlace;
import main.Repository.DoctorRepository;

@Service
public class DoctorService_2 implements DoctorService{

	@Autowired
	private DoctorRepository repo;
	
	@Autowired
	private MappingPlace mapper;
	
	//@Autowired
	//private RestTemplate restTemplate;
	
	@Autowired
	private AppointmentClient appointmentclient;

	
	//post
	
	
	
	@Override
	public DoctorResponseDto createDoctor(@Valid DoctorRequestDto dto) {
		// TODO Auto-generated method stub
		
		if(repo.existsByEmail(dto.getEmail())) {
			
			throw new EmailExistsException("this email already exists please use new one");
		}
		
		
		Doctor doctor=mapper.toEntity(dto);
		
		Doctor doctor_2=repo.save(doctor);
		
		return  mapper.toDto(doctor_2);
	}


	
	//getbyid 
	
	
	
	@Override
	public DoctorResponseDto getbyid(Long id) {
		// TODO Auto-generated method stub
		
		Doctor doctor=repo.findById(id) 
				     .orElseThrow(()->new IdNotFoundException("id is not found"));
		
		
		return mapper.toDto(doctor);
	}



	
	//getAll
	
	
	
	
	@Override
	public List<DoctorResponseDto> getAll() {
		// TODO Auto-generated method stub
		
		
		List<Doctor>doctorlist=repo.findAll();
		
		
		
		
		return doctorlist.stream().map(mapper::toDto).toList();
	}



	
	
	
	//get specialists
	
	
	@Override
	public List<DoctorResponseDto> getSpecialists(Department department) {
		// TODO Auto-generated method stub
		
		List<Doctor>list=repo.findByDepartment(department);
		
		
		return list.stream().map(mapper::toDto).toList();
	}


	
	
	

	
	//count total doctors

	@Override
	public Long countDoctors() {
		// TODO Auto-generated method stub
		return repo.count();
	}


	
	//count all active doctors

	@Override
	public Long getActiveDoctorsCount() {
		// TODO Auto-generated method stub
		return repo.countByActiveTrue();
	}



	
	   //count all inactive doctors
	@Override
	public Long getInactiveDoctorsCount() {
		// TODO Auto-generated method stub
		return repo.countByActiveFalse();
	}



	
	//update active to inactive 
	@Override
	public void updateActiveToInactive(Long id) {
		// TODO Auto-generated method stub
		
		 Doctor doctor=repo.findById(id)  
				 .orElseThrow(()->new RuntimeException("id is not found"));
		 
		 doctor.setActive(false);
		 
		 repo.save(doctor);
		
	}

	
	
	   // update inactive to active 
		@Override
		public void updateInactiveToActive(Long id) {
			// TODO Auto-generated method stub
			
			Doctor doctor=repo.findById(id) 
					.orElseThrow(()->new RuntimeException("id is not found"));
			
			doctor.setActive(true);
			
			repo.save(doctor);
			
		}


		
		//doctor analytics
		
		

		@Override
		public DoctorAnalyticsDto doctorAnalytics(Long id) {
			// TODO Auto-generated method stub
			
			 Doctor doctor = repo.findById(id)
			            .orElseThrow(() ->
			                    new IdNotFoundException("Doctor id not found"));
			
			
			List<AppointmentResponseDto>appointments=appointmentclient.getAllAppointments();
			
			
			List<AppointmentResponseDto>doctorAppointments=appointments.stream().
					
					filter(e->e.getDoctorId().equals(id)).toList();
			
			//create empty DoctorAnalyticsDto object
			
			DoctorAnalyticsDto dto=new DoctorAnalyticsDto();
			
			//set total apppointments
			dto.setTotalAppointments(doctorAppointments.size());
			
			
			//completed appointments
			
			dto.setCompletedAppointments(doctorAppointments.stream()
					.filter(e->e.getAppointmentStatus()==AppointmentStatus.COMPLETED).count());
			
			
			//cancelled appointments
			
			dto.setCancelledAppointments(doctorAppointments.stream()
					  .filter(b->b.getAppointmentStatus()==AppointmentStatus.CANCELLED).count());
			
			
			//pending appointments
			
			dto.setPendingAppointments(doctorAppointments.stream()
					.filter(c->c.getAppointmentStatus()==AppointmentStatus.PENDING).count());
			
			
			//patient treated
			
			dto.setPatientsTreated(doctorAppointments.stream().
					filter(a -> a.getAppointmentStatus() == AppointmentStatus.COMPLETED).
					  
					map(AppointmentResponseDto::getPatientId).distinct().count());
			
			//set review rate
			
			dto.setAverageRating(doctor.getReviewRating());
			
			
			//consultation hour
			
			Duration duration=Duration.between(doctor.getConsultationStartTime(),
					                                    doctor.getConsultationEndTime());
			dto.setConsultationHours(duration.toHours()+"hours");
			
			return dto;
		}



		//doctor department analytics---------->each department how many doctors has
		@Override
		public Map<Department, Long> getDoctorDepartmentAnalytics() {
			// TODO Auto-generated method stub
			
			List<Doctor>doctorlist=repo.findAll();
			
			return doctorlist.stream().collect(Collectors.groupingBy(Doctor::getDepartment,
					                                      Collectors.counting()));
			
		}

		

}



