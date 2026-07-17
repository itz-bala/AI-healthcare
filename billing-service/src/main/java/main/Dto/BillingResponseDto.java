package main.Dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import main.Entity.PaymentMethod;
import main.Entity.PaymentStatus;

@Data
public class BillingResponseDto {
	
	
   private Long id;
	
	
	
	private Long patientId;
	
	
	private Long doctorId;
	
	
	
	private Long appointmentId;
	
	 private Long prescriptionId;
		
		
   private Long labId;
		
	private Double discount;
	
	
	private Double tax;
	
	
	private Double totalAmount;
	
	
	private Double paidAmount;
	
	
	private Double pendingAmount;
	
	
	
	
	private PaymentStatus paymentStatus;
	
	
	private PaymentMethod paymentMethod;
	
	private LocalDateTime billingDate;
	
	private LocalDateTime createdTime;
	
	
	private LocalDateTime updatedTime;
	
	
	
	
	
	

}
