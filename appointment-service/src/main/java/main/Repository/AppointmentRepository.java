package main.Repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import main.Entity.Appointment;
import main.Entity.AppointmentStatus;

public interface AppointmentRepository extends JpaRepository<Appointment,Long>{
	
	
	long countByAppointmentDateBetween(LocalDateTime start,
            LocalDateTime end);
	
	
	
	
	long countByAppointmentStatus(AppointmentStatus appointmentStatus);
	long count();
	
	
	
	long countByDoctorId(Long doctorId);

    long countByDoctorIdAndAppointmentStatus(
            Long doctorId,
            AppointmentStatus appointmentStatus);
	
	
	long countDistinctPatientIdByDoctorId(Long doctorId);
	 
	
	List<Appointment>findByAppointmentDateBetween(LocalDateTime start,LocalDateTime end);
	
	long countByPatientId(Long patientId);
	
	
	List<Appointment>findByPatientId(Long PatientId);

}
