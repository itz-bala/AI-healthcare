package main.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import main.Entity.Prescription;

public interface PrescriptionRepository extends JpaRepository<Prescription,Long>{
	
	List<Prescription>findByPatientId(Long patientId);
	
	List<Prescription>findByDoctorId(Long doctorId);

}
