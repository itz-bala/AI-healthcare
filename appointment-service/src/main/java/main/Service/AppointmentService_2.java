package main.Service;


import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import main.Dto.AppointmentAnalyticsDto;
import main.Dto.AppointmentRequestDto;
import main.Dto.AppointmentResponseDto;
import main.Dto.DoctorAnalyticsDto;
import main.Dto.DoctorResponseDto;
import main.Dto.NotificationDto;
import main.Dto.PatientResponseDto;
import main.Entity.Appointment;
import main.Entity.AppointmentStatus;
import main.Entity.Department;
import main.Exception.DoctorNotFoundException;
import main.Exception.IdNotFoundException;
import main.Exception.PatientNotFoundException;
import main.FeignClientAnnotations.DoctorClient;
import main.FeignClientAnnotations.PatientClient;
import main.Mapper.MappingPlace;
import main.Repository.AppointmentRepository;

@Service
public class AppointmentService_2 implements AppointmentService{
	
	

	
	@Autowired
	private AppointmentRepository repo;
	
	
	
	
	@Autowired
	private RabbitmqProducer producer;
	
	
	@Autowired
	private MappingPlace mapper;


	   @Autowired
	   private  PatientClient patientclient;
	   
	   
	   @Autowired
	   private DoctorClient doctorclient;
	
	//post
	
	
	@Override
	public AppointmentResponseDto createBooking(AppointmentRequestDto dto) {
		// TODO Auto-generated method stub
		
		
		
		PatientResponseDto patient;
		DoctorResponseDto doctor;
		
		
		
		
		try {
			
		patient=patientclient.getPatient(dto.getPatientId());
		
		
		}catch(HttpClientErrorException.NotFound  e) {
			
			
			throw new PatientNotFoundException("patientid is not exists in patient databse");
		}
		
		
		
		
		
		try {
		
			doctor=doctorclient.getDoctor(dto.getDoctorId());
		
		}catch(HttpClientErrorException.NotFound e) {
			
			throw new DoctorNotFoundException("doctor id is not exists doctor  database");
		}
		
		
		long appointmentCount = repo.countByPatientId(patient.getId());
		
		Appointment appointment=mapper.toEntity(dto);
		
		    appointment.setAppointmentStatus(AppointmentStatus.BOOKED);
		
		Appointment appointment_2=repo.save(appointment);
		
		    //update firstvisit is false
		
		
		
		
		if (appointmentCount == 1 && patient.getFirstVisit()) {
		    patientclient.updateFirstVisist(patient.getId());
		}
		
		

   //notification
		
		
		NotificationDto notification = new NotificationDto();

		notification.setPatientId(patient.getId());
		notification.setPatientName(patient.getName());
		notification.setPatientEmail(patient.getEmail());
		notification.setPatientPhonenum(patient.getPhonenum());

		notification.setDoctorId(doctor.getId());
		notification.setDoctorName(doctor.getName());
		notification.setDoctorEmail(doctor.getEmail());
		notification.setDoctorPhonenum(doctor.getPhonenum());

		notification.setAppointmentDate(appointment_2.getAppointmentDate());

		producer.send(notification);
		
		return  mapper.toDto(appointment_2);
		
		
		
		
		
		
		
	}


	
	

	//get by id
	
	
	@Override
	public AppointmentResponseDto getbyid(Long id) {
		// TODO Auto-generated method stub
		
		Appointment appointment=repo.findById(id) 
				        .orElseThrow(()->new IdNotFoundException("appointment id is not found"));
		
		
		
		return mapper.toDto(appointment);
	}



      //getAll appointments

	@Override
	public List<AppointmentResponseDto> getAll() {
		// TODO Auto-generated method stub
		
		List<Appointment>appointmentlist=repo.findAll();
		
		
		return appointmentlist.stream().map(mapper::toDto).toList();
	}



	
	//analytics
	
   @Override
	public AppointmentAnalyticsDto analytics() {
		// TODO Auto-generated method stub
	
	 AppointmentAnalyticsDto dto = new AppointmentAnalyticsDto();

	    List<Appointment> appointments = repo.findAll();
	    
	  
	    
	    
	    
	    LocalDateTime now=LocalDateTime.now();
	    
	    
	  //daily appointments
	    
	    Long daily=repo.countByAppointmentDateBetween(now.toLocalDate().atStartOfDay(),
	    		                             now.toLocalDate().plusDays(1).atStartOfDay());
	    
	    
	    dto.setDailyAppointments(daily);
	    
	    
	    //weelkly appointments
	    
	    Long weekly=repo.countByAppointmentDateBetween(now.toLocalDate().minusDays(6).atStartOfDay(),
	    		                                      now.toLocalDate().plusDays(1).atStartOfDay());
	    
	    dto.setWeeklyAppointments(weekly);
	    
	    //monthy appointments
	    
	    Long monthy=repo.countByAppointmentDateBetween(now.toLocalDate().minusMonths(1).atStartOfDay(),
	    		                                    now.toLocalDate().plusDays(1).atStartOfDay());
	    
	    
	    dto.setMonthlyAppointments(monthy);
	    
	    
	    
	    //each doctor id contains how many appointments counting
	    
	    
	    
	    Map<String,Long>doctormap=appointments.stream()
	    		 .collect(Collectors.groupingBy(a->a.getDoctorId().toString(), 
	    				                                  Collectors.counting()));
	    
	    
	    dto.setAppointmentsByDoctor(doctormap);
	    
	    
	
	    
	    //peek booking hour
	    
	    Map<String, Long> hourMap = appointments.stream()
	            .collect(Collectors.groupingBy(
	                    a -> String.valueOf(a.getBookingTime().getHour()),
	                    Collectors.counting()));

	    Map<String, Long> peakHourMap = hourMap.entrySet()
	            .stream()
	            .max(Map.Entry.comparingByValue())
	            .map(entry -> Map.of(entry.getKey(), entry.getValue()))
	            .orElse(new HashMap<>());

	    dto.setPeakBookingHours(peakHourMap);
	    
	    
	    
	    
	    
	    
	    
	    
	    //each departments how may appointments contains 
	    
	    Map<String, Long> departmentMap = new HashMap<>();

	    for (Appointment a : appointments) {

	        DoctorResponseDto doctor = doctorclient.getDoctor(a.getDoctorId());

	        String department = doctor.getDepartment().toString();

	        departmentMap.put(
	                department,
	                departmentMap.getOrDefault(department, 0L) + 1
	        );
	    }

	    dto.setAppointmentsByDepartment(departmentMap);
	    
	    // Average Consultation Time
	    dto.setAverageConsultationTime(30.0);
	
		return dto;
	}



   //today appointment counts

@Override
public Long todayCount() {
	// TODO Auto-generated method stub
	
	
	
LocalDateTime now=LocalDateTime.now();
    
     Long count=repo.countByAppointmentDateBetween(now.toLocalDate().atStartOfDay(),
    		                                 now.toLocalDate().plusDays(1).atStartOfDay());
   

    
return count;

}

//total  appointments count
@Override
public Long getAllAppointmentscount() {
	// TODO Auto-generated method stub
	return repo.count();
}




    //completed appointments counts
@Override
public Long completedAppointments() {
	// TODO Auto-generated method stub
	return repo.countByAppointmentStatus(AppointmentStatus.COMPLETED);
}




    //cancelled appointments counts
@Override
public Long cancelledAppointments() {
	// TODO Auto-generated method stub
	return repo.countByAppointmentStatus(AppointmentStatus.CANCELLED);
}






//update booking to cancell


@Override
public void updateToCancelled(Long id) {
	// TODO Auto-generated method stub
	
	Appointment appointment=repo.findById(id) 
			       .orElseThrow(()->new RuntimeException("id is not found"));
	
	
	  appointment.setAppointmentStatus(AppointmentStatus.CANCELLED);
	  
	  repo.save(appointment);
	
}




   //update booking completed
@Override
public void updateToCompleted(Long id) {
	// TODO Auto-generated method stub
	

	Appointment appointment=repo.findById(id) 
			       .orElseThrow(()->new RuntimeException("id is not found"));
	
	
	  appointment.setAppointmentStatus(AppointmentStatus.COMPLETED);
	  
	  repo.save(appointment);
	
}





  //appointment department analytics

@Override
public Map<Department, Long> getAppointmentDepartmentAnalytics() {
	// TODO Auto-generated method stub
	
	
	
//	List<Appointment>appointmentlist=repo.findAll();
//	
//	
//	return appointmentlist.stream().collect(Collectors.groupingBy(
//			
//			    a->doctorclient.getDoctor(a.getDoctorId()).getDepartment(),
//			                    Collectors.counting()));
	
	
	
	  List<Appointment> appointmentList = repo.findAll();

	    return appointmentList.stream()
	            .collect(Collectors.groupingBy(
	                    Appointment::getDepartment,
	                    Collectors.counting()));
}



//appointment trends 

@Override
public Map<String, Long> last7DaysAppointments() {
	// TODO Auto-generated method stub
	
	LocalDate now=LocalDate.now();
	
	LocalDateTime start=now.minusDays(6).atStartOfDay();
	LocalDateTime end=now.plusDays(1).atStartOfDay();
	
	
	   List<Appointment>list=repo.findByAppointmentDateBetween(start, end);
	   
	   
	   
	return list.stream().collect(Collectors.groupingBy(a->a.getAppointmentDate().toLocalDate().toString()
			                        
			                                   , TreeMap::new,Collectors.counting()));
}






  
	
	

	


	}
	
	
	
	


