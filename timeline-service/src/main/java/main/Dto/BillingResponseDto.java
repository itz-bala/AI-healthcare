package main.Dto;

import java.time.LocalDateTime;

import lombok.Data;
import main.Enum.PaymentMethod;
import main.Enum.PaymentStatus;



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
