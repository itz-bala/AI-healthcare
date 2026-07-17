package main.Consumer;


import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.Dto.NotificationDto;
import main.Email.EmailService;
import main.SMS.SmsService;


@Service
public class RabbitConsumer {

	
	
	@Autowired
	private EmailService emailservice;
	
	@Autowired
	private SmsService smsservice;
	
	
	

	    @RabbitListener(queues = "ap.queue")
	    public void consume(NotificationDto dto) {

	       
	    	
	    	
	    	// Email to Patient
	    	emailservice.sendEmail(
	    	    dto.getPatientEmail(),
	    	    "Appointment Confirmation",
	    	    "Hello " + dto.getPatientName()
	    	    + ",\n\nYour appointment with Dr. "
	    	    + dto.getDoctorName()
	    	    + " has been booked successfully."
	    	    + "\n\nDate: " + dto.getAppointmentDate()
	    	    + "\n\nThank You."
	    	);

	    	// Email to Doctor
	    	emailservice.sendEmail(
	    	    dto.getDoctorEmail(),
	    	    "New Appointment Booked",
	    	    "Hello Dr. " + dto.getDoctorName()
	    	    + ",\n\nA new appointment has been booked."
	    	    + "\n\nPatient: " + dto.getPatientName()
	    	    + "\nDate: " + dto.getAppointmentDate()
	    	    + "\n\nPlease be available."
	    	);
	    	
	  
	    	System.out.println("mail sent");
	    	
	    	
//	    	 // Patient SMS
//	        smsservice.sendSms(
//	                dto.getPatientPhonenum(),
//	                "Hello " + dto.getPatientName()
//	                + ", your appointment has been booked."
//	                + "\nDoctor: " + dto.getDoctorName()
//	                + "\nDate: " + dto.getAppointmentDate()
//	        );
//
//	        // Doctor SMS
//	        smsservice.sendSms(
//	                dto.getDoctorPhonenum(),
//	                "Hello Dr. " + dto.getDoctorName()
//	                + ", a new appointment has been booked."
//	                + "\nPatient: " + dto.getPatientName()
//	                + "\nDate: " + dto.getAppointmentDate()
//	        );
//
//	        System.out.println("Emails and SMS sent successfully.");
	        
	        
	       
	    }


}
