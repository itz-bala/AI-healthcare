package main.Entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

@Entity
@Table(name="billing_table")
@Data
public class Billing {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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
	
	
	
	@Enumerated(EnumType.STRING)
	private PaymentStatus paymentStatus;
	
	
	
	@Enumerated(EnumType.STRING)
	private PaymentMethod paymentMethod;
	
	
	@CreationTimestamp
	private LocalDateTime billingDate;
	@CreationTimestamp
	private LocalDateTime createdTime;
	
	@UpdateTimestamp
	private LocalDateTime updatedTime;
	
	
	
	
	
	
	
	
	
	

}
