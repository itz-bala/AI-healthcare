package main.Dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import main.Entity.PaymentMethod;
import main.Entity.PaymentStatus;

@Data
public class BillingRequestDto {
	
	
	
	
	
    private Long id;
	
	
	
    @NotNull(message="patient id must be")
	private Long patientId;
	
	
    @NotNull(message="doctorid must be")
	private Long doctorId;
	
	
	@NotNull(message="appointment id is required")
	private Long appointmentId;
	
	
	
	@NotNull(message="prescription id must be")
   private Long prescriptionId;
	
	@NotNull(message="lab id must be")
	private Long labId;
	
	
	
	
	
	
	
	private Double discount;
	
	
	private Double tax;
	
	
	
	
	private Double paidAmount;
	
	
	
	
	private PaymentMethod paymentMethod;
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
