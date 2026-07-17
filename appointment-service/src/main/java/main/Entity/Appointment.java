package main.Entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

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
@Table(name="appointment")
@Data
public class Appointment {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message="patient id is ,must be")
	private  Long  patientId;
	
	
	@NotNull(message="doctor id must be")
	private Long doctorId;
	
	@Enumerated(EnumType.STRING)
	private Department department;
	
	@NotNull(message="appointment date and time is m ust be")
	private LocalDateTime  appointmentDate;
	
	
	@Enumerated(EnumType.STRING)
	private AppointmentStatus  appointmentStatus;

     @CreationTimestamp
    private LocalDateTime    bookingTime;
	
	
	
	
	

}
