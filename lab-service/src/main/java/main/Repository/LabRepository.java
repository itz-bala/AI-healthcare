package main.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import main.Entity.Lab;
import main.Entity.LabStatus;

public interface LabRepository extends JpaRepository<Lab,Long>{
	
	
	 long countByLabStatus(LabStatus labStatus);
	 
	 List<Lab>findByPatientId(Long patientId);

}
