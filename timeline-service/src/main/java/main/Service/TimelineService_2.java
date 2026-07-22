package main.Service;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.Dto.TimelineItemDto;
import main.Dto.TimelineResponseDto;
import main.Enum.TimelineStatus;
import main.Feign.AppointmentClient;
import main.Feign.BillingClient;
import main.Feign.LabClient;
import main.Feign.PrescriptionClient;

@Service
public class TimelineService_2 implements TimelineService{
	
	
	@Autowired
	private  AppointmentClient appointmentclient;
	
	@Autowired
	private BillingClient billingclient;
	
	@Autowired
	private LabClient labclient;
	
	@Autowired
	private PrescriptionClient prescriptionclient;

	@Override
	public TimelineResponseDto getTimelineResponse(Long patientId, 
			LocalDate fromDate, 
			LocalDate toDate,
			String type, String sort, int page, int size) {
		// TODO Auto-generated method stub
		
		
		List<TimelineItemDto>timeline=new ArrayList<>();
		
		  // Appointment Timeline
		
		if(type==null||type.equalsIgnoreCase("APPOINTMENT")) {
			
        List<TimelineItemDto> appointmentTimeline = appointmentclient
                .getAllPatientAppointments(patientId)
                .stream()
                .filter(x->fromDate==null||!x.getAppointmentDate().toLocalDate().isBefore(fromDate))
                
                .filter(y->toDate==null||!y.getAppointmentDate().toLocalDate().isAfter(toDate))
                .map(a -> {

                    TimelineItemDto dto_1 = new TimelineItemDto();

                    dto_1.setEventDate(a.getAppointmentDate());
                    dto_1.setRecordType("APPOINTMENT");
                    dto_1.setTitle("Appointment");
                    dto_1.setDescription(a.getDepartment().name());
                    dto_1.setTimelineStatus(
                            TimelineStatus.valueOf(a.getAppointmentStatus().name()));

                    return dto_1;
                })
                .toList();

        
               timeline.addAll(appointmentTimeline);
        }
        
        //Lab timeline
        
        if(type==null||type.equalsIgnoreCase("LAB")) {
        
        List<TimelineItemDto>labtimeline=labclient.getAllPatientLabReports(patientId) 
        		                           .stream()
        		                           .filter(x->fromDate==null||!x.getTestDate().isBefore(fromDate))
        		                           .filter(y->toDate==null||!y.getTestDate().isAfter(toDate))
        		                           .map(l->{
        		                        	   
        		                        	   
        		     TimelineItemDto dto_2=new TimelineItemDto();
        		     
        		     dto_2.setEventDate(l.getTestDate().atStartOfDay());
        		     dto_2.setRecordType("LAB");
        		     dto_2.setTitle("lab");
        		     dto_2.setDescription(l.getTestName());
        		     dto_2.setTimelineStatus(TimelineStatus.valueOf(l.getLabStatus().name()));
        		                        	   
        		                        	   
        		        return dto_2;                	   
        		                        	   
        		                        	   
        		                           }).toList();
        
        
        
              timeline.addAll(labtimeline);
        }
        
        //prescription
      
        if(type==null||type.equalsIgnoreCase("PRESCRIPTION")) {
        	
        List<TimelineItemDto>prescriptiontimeline=prescriptionclient.getAllPatientsPrescriptionDetails(patientId)
        
                                                   .stream()
                                                   .filter(x->fromDate==null||!x.getPrescriptionDate().toLocalDate().isBefore(fromDate))
                                                   .filter(y->toDate==null||!y.getPrescriptionDate().toLocalDate().isAfter(toDate))
                                                   .map(p->{
                                                	   
                                                	   
                                                	   
                                TimelineItemDto dto_3=new TimelineItemDto();                     	   
                                                	   
                                                	   
                                     dto_3.setEventDate(p.getPrescriptionDate());
                                     dto_3.setRecordType("PRESCRIPTION");
                                     dto_3.setTitle("prescription");
                                     dto_3.setDescription(p.getDiagnosis());
                                     dto_3.setTimelineStatus(TimelineStatus.GENERATED);
                                                	   
                                          
                                     
                                     return dto_3;
                                                   }).toList();
        
            timeline.addAll(prescriptiontimeline);
        } 
            
            //billing
        
        if(type==null||type.equalsIgnoreCase("BILLING")) {
            
             List<TimelineItemDto>billingtimeline=billingclient.getAllPatientBillings(patientId)
                                                     .stream()
                                                     .filter(x->fromDate==null||!x.getBillingDate().toLocalDate().isBefore(fromDate))
                                                     .filter(y->toDate==null||!y.getBillingDate().toLocalDate().isAfter(toDate))
                                                     .map(b->{
                                                    	 
                                                    	 
                            TimelineItemDto dto_4=new TimelineItemDto();                            	 
                                                    	 
                                                    	 
                                dto_4.setEventDate(b.getBillingDate()) ;
                                dto_4.setRecordType("BILLING");
                                dto_4.setTitle("billing");
                                dto_4.setDescription("AMOUNT"+b.getTotalAmount());
                                dto_4.setTimelineStatus(TimelineStatus.valueOf(b.getPaymentStatus().name()));
                                                    	 
                                                    	 
                                      return dto_4;              	 
                                                     }).toList();
        
        
          
                 timeline.addAll(billingtimeline);
        
          }
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        //sort
        
        
        
        if("asc".equalsIgnoreCase(sort)) {
        	
        	timeline.sort(Comparator.comparing(TimelineItemDto::getEventDate));
        }else {
        	
        	timeline.sort(Comparator.comparing(TimelineItemDto::getEventDate).reversed());
        }
        
        
        //pagination
        
        
        int start=page*size;
        
        int end=Math.min(start+size, timeline.size());
        
        
        List<TimelineItemDto> paginatedRecords = new ArrayList<>();
        
        if (start < timeline.size()) {
            paginatedRecords = timeline.subList(start, end);
        }
        
        
        // Response
        TimelineResponseDto response = new TimelineResponseDto();

        response.setRecords(paginatedRecords);
        response.setCurrentPage(page);
        response.setTotalPages(
                (int) Math.ceil((double) timeline.size() / size));
        response.setTotalElements(timeline.size());

        return response;

        
	}
	
	
	     
	
	
	

}
