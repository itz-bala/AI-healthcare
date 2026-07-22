package main.Service;



import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import jakarta.validation.Valid;
import main.Dto.AppointmentResponseDto;
import main.Dto.BillingAnalyticsDto;
import main.Dto.BillingRequestDto;
import main.Dto.BillingResponseDto;
import main.Dto.DoctorResponseDto;
import main.Dto.LabResponseDto;
import main.Dto.PatientResponseDto;
import main.Dto.PrescriptionMedicineResponseDto;
import main.Dto.PrescriptionResponseDto;
import main.Entity.Billing;
import main.Entity.Department;
import main.Entity.PaymentStatus;
import main.Exception.InvalidPaymentException;
import main.Exception.ResourceNotFoundException;
import main.FeignClientAnnotations.AppointmentClient;
import main.FeignClientAnnotations.DoctorClient;
import main.FeignClientAnnotations.LabClient;
import main.FeignClientAnnotations.PatientClient;
import main.FeignClientAnnotations.PrescriptionClient;
import main.Mapper.ModelConfig;
import main.Repository.BillingRepository;

@Service
public class BillingService_2 implements BillingService{
	
	
	private final   BillingRepository repo;
	
	
	private final ModelConfig mapper;
	
	//private final RestTemplate restTemplate;
	
	private PatientClient patientclient;
	
	private DoctorClient doctorclient;
	
	private AppointmentClient appointmentclient;
	
	
	private  LabClient labclient;
	
	private PrescriptionClient prescriptionclient;
	
	public BillingService_2(BillingRepository repo,
			                ModelConfig mapper,
			                PatientClient patientclient,
			                DoctorClient doctorclient,
			                AppointmentClient appointmentclient,
			                LabClient labclient,
			                PrescriptionClient prescriptionclient
			                
			) {
		
		
		this.repo=repo;
		
		this.mapper=mapper;
		this.patientclient=patientclient;
		this.doctorclient=doctorclient;
		this.appointmentclient=appointmentclient;
		this.labclient=labclient;
		this.prescriptionclient=prescriptionclient;
	}


	
	//post

	@Override
	public BillingResponseDto createBilling(@Valid BillingRequestDto dto) {
		
		
		PatientResponseDto patient;
		DoctorResponseDto doctor;
		AppointmentResponseDto appointment;
		LabResponseDto lab;
		PrescriptionResponseDto prescription;
		
		
		
		
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
		
		
		//..........................................................
		
		
		//get lab details
		
		
		try {
			
        lab=labclient.getLab(dto.getLabId());

		}catch(HttpClientErrorException e) {
			
			throw new ResourceNotFoundException("lab id is not exists");
		}
		
		
		
		//.................................................
		
		
		//get prescription details  
		
		
		try {
			
        prescription=prescriptionclient.getPrescription(dto.getPrescriptionId());
			
		}catch(HttpClientErrorException e) {
			
			
			throw new ResourceNotFoundException("prescription id is not found");
		}
		
		
		
		
		Billing billing=mapper.toEntity(dto);
		
		  
		if(!appointment.getPatientId().equals(billing.getPatientId())) {
			
			throw new ResourceNotFoundException("patient does not belongs to this appointment");
		}
		
		
		if(!appointment.getDoctorId().equals(billing.getDoctorId())) {
			
			
			throw new ResourceNotFoundException("doctor does not belongs to this appointment");
		}
		   
		   
	
		//get fees from services
		
		Double consultationfees=doctor.getConsultationFees();//doctor-service
		Double labfees=lab.getLabFees();                         //lab-service
		Double medicinefees=prescription.getMedicines().stream()   //pprescription-service
				  .mapToDouble(PrescriptionMedicineResponseDto::getMedicineFees).sum();
		
		
		
		
		//totalbill
		Double totalbill=consultationfees+labfees+medicinefees+billing.getTax()-billing.getDiscount();
		
		billing.setTotalAmount(totalbill);
		
		//pendingbill
		
		Double pendingbill=totalbill-billing.getPaidAmount();
		billing.setPendingAmount(pendingbill);
		
		
		//check payment status
		
		if(billing.getPaidAmount().doubleValue()==0.0) {
			
			billing.setPaymentStatus(PaymentStatus.PENDING);
		}else if(billing.getPaidAmount()<totalbill) {
			
			billing.setPaymentStatus(PaymentStatus.PARTIALLY_PAID);
		}else if(billing.getPaidAmount()>totalbill) {
			
			throw new InvalidPaymentException("please send  valid money");
			
		}else if(billing.getPaidAmount()==totalbill){
			
			 System.out.println("payment is successfull");
		}else {
			
			billing.setPaymentStatus(PaymentStatus.CANCELLED);
		}
		
		

	Billing billing_2=repo.save(billing);
	
	
	return mapper.toDto(billing_2);
		
	}


	
	//getbyid

	@Override
	public BillingResponseDto getbyid(Long id) {
		// TODO Auto-generated method stub
		
		
		Billing billing=repo.findById(id)
				.orElseThrow(()->new RuntimeException("id is not exists"));
		
		
		
		return mapper.toDto(billing);
	}



	
	//getAll
	
	
	@Override
	public List<BillingResponseDto> getAll() {
		// TODO Auto-generated method stub
		
		List<Billing>billinglist=repo.findAll();
		
		
		return billinglist.stream().map(mapper::toDto).toList();
	}


	
	//billing Analytics
	

	@Override
	public BillingAnalyticsDto getBillingAnalytics() {
		// TODO Auto-generated method stub
		
		
		BillingAnalyticsDto dto=new BillingAnalyticsDto();
		
		
		LocalDateTime now=LocalDateTime.now();
		
		
		//daily revenue
		
		LocalDateTime  startoftheday=LocalDate.now().atStartOfDay();
		
		Double dailyRevenue=repo.findByBillingDateBetween(startoftheday, now).stream()
				           .mapToDouble(Billing::getTotalAmount).sum();
		
		
		dto.setDailyRevenue(dailyRevenue);
		
		
		
		// Weekly Revenue (Last 7 Days)

		LocalDateTime startOfWeek = now.toLocalDate()
		                               .minusDays(6)
		                               .atStartOfDay();

		LocalDateTime endOfWeek = now.toLocalDate()
		                             .plusDays(1)
		                             .atStartOfDay();

		Double weeklyRevenue = repo.findByBillingDateBetween(startOfWeek, endOfWeek)
		        .stream()
		        .mapToDouble(Billing::getTotalAmount)
		        .sum();

		dto.setWeeklyRevenue(weeklyRevenue);
		
		//monthly Revenue
		
		LocalDateTime startofthemonth=now.minusMonths(1);
		
		Double monthlyRevenue=repo.findByBillingDateBetween(startofthemonth,now).stream() 
				                  .mapToDouble(Billing::getTotalAmount).sum();
		
		dto.setMonthlyRevenue(monthlyRevenue);
		
		
	
		
		//doctor revenue
		
		//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		List<Billing>billinglist=repo.findAll();
		//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		
		
		Map<String,Double>doctorRevenue=billinglist.stream()
				                 
				                   .collect(Collectors.groupingBy(bill->doctorclient.getDoctor(
				                		   
				                		     bill.getDoctorId()).getName(),Collectors.summingDouble(
				                		    		 Billing::getTotalAmount)));
		
		
		dto.setRevenueByDoctor(doctorRevenue);
		
		
		//department revenue
		
		Map<String,Double>departmentRevenue=billinglist.stream()
				                    
				                       .collect(Collectors.groupingBy(bill->doctorclient.getDoctor(
				                    		   
				                    		   
				                    		  bill.getDoctorId()).getDepartment().name(),
				                    		   Collectors.summingDouble(Billing::getTotalAmount)));
		dto.setRevenueByDepartment(getRevenueByDepartment());
		
		
		//revenue by payment method
		
		
		Map<String, Double> paymentRevenue =
		        billinglist.stream()
		        .collect(Collectors.groupingBy(

		                bill -> bill.getPaymentMethod().name(),

		                Collectors.summingDouble(Billing::getTotalAmount)

		        ));

		dto.setRevenueByPaymentMethod(paymentRevenue);
		
		
		//total revenue
		
		Double totalRevenue=billinglist.stream() 
				 .mapToDouble(Billing::getTotalAmount).sum();
		
		dto.setTotalRevenue(totalRevenue);
		
		//pending revenue
		
		Double pendingRevenue=billinglist.stream()
				   .mapToDouble(Billing::getPendingAmount).sum();
		
		dto.setPendingPayments(pendingRevenue);
		
		return dto;
	}


	
	//billing department analytics
	

	@Override
	public Map<Department, Long> getBillingDepartmentAnalytics() {
		// TODO Auto-generated method stub
		
		
		List<Billing>billinglist=repo.findAll();
		
		
		
		return billinglist.stream().collect(Collectors.groupingBy(a->doctorclient.getDoctor(
				
				   a.getDoctorId()).getDepartment(),Collectors.counting()));
	}


	
	//billing trends 

	@Override
	public Map<String, Double> last30DaysRevenue() {
		// TODO Auto-generated method stub
		
		 LocalDate today = LocalDate.now();

		    LocalDateTime start = today.minusDays(29).atStartOfDay();
		    LocalDateTime end = today.plusDays(1).atStartOfDay();

		    List<Billing> list =
		            repo.findByBillingDateBetween(start, end);

		    return list.stream()
		            .collect(Collectors.groupingBy(
		                    b -> b.getBillingDate().toLocalDate().toString(),
		                    TreeMap::new,
		                    Collectors.summingDouble(Billing::getTotalAmount)
		            ));
	
	}


	
	//  department revenue

	@Override
	public Map<String, Double> getRevenueByDepartment() {
		// TODO Auto-generated method stub
		
		
		
		List<Billing> billinglist = repo.findAll();

	    return billinglist.stream()
	            .collect(Collectors.groupingBy(

	                    bill -> doctorclient
	                            .getDoctor(bill.getDoctorId())
	                            .getDepartment()
	                            .name(),

	                    Collectors.summingDouble(Billing::getTotalAmount)

	            ));
	}



	
	    //getAll patient bills
	@Override
	public List<BillingResponseDto> getPatientBill(Long patientId) {
		// TODO Auto-generated method stub
		
		List<Billing>patientbills=repo.findByPatientId(patientId);
		
		return patientbills.stream().map(mapper::toDto).toList();
	}
	
	
	
	
	
	
	
	
	
}
	
	


