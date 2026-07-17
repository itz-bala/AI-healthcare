package main.Entity;

import java.time.LocalDate;
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
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name="lab_report")
@Data
public class Lab {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message="patient id is must be")
	private  Long patientId;
	
	@NotNull(message="doctor id is must be")
	private Long  doctorId;
	
	@NotNull(message="appointment id is must be")
	private Long appointmentId;
	
	
	private String testName;
	
	
	
	
	@NotNull(message="lab status must be")
	@Enumerated(EnumType.STRING)
	private  LabStatus labStatus;
	
	private String result;
	
	@NotNull(message="labfees must be")
	private Double labFees;
	
	@CreationTimestamp
	private LocalDate testDate;
     @CreationTimestamp
	private LocalDate reportDate;
     @CreationTimestamp
	private LocalDateTime createdAt;
       @UpdateTimestamp
	private LocalDateTime updatedAt;

}
