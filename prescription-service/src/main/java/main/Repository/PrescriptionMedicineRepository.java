package main.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import main.Entity.PrescriptionMedicine;

public interface PrescriptionMedicineRepository extends JpaRepository<PrescriptionMedicine,Long>{

	
	
	List<PrescriptionMedicine>findByPrescriptionId(Long prescriptionId);
	
	
}
