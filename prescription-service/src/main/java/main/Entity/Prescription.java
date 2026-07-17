package main.Entity;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name="prescription_table")
@Data
public class Prescription {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message="patient id is must be")
	private Long patientId;
	
	@NotNull(message="doctor id is must be")
	private Long doctorId;
	
	@NotNull(message="appointment id is must be")
	private Long appointmentId;
	
	@NotBlank(message="diagnosis must be")
	private String diagnosis;
	
	@NotBlank(message="symptoms must be")
	private String symptoms;
	
	private String notes;
	
	@CreationTimestamp
	private LocalDateTime prescriptionDate;
	
	@CreationTimestamp
	private LocalDateTime  createdTime;
	
	@UpdateTimestamp
	private LocalDateTime updatedTime;
	
	
	
	@OneToMany(mappedBy="prescription",cascade=CascadeType.ALL,orphanRemoval=true,fetch=FetchType.LAZY)
	@JsonManagedReference
	private List<PrescriptionMedicine>medicines;
	

}
