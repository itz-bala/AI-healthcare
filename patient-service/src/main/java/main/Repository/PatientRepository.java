package main.Repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import main.Entity.Patient;

public interface PatientRepository extends JpaRepository<Patient,Long>{
	
	
	boolean existsByEmail(String email);
	
	Optional<Patient>findById(Long id);
	
	long count();
	
	long countByActiveTrue();
	long countByActiveFalse();
	
	
	
	
	
	
	long countByFirstVisitTrue();
	
	long countByFirstVisitFalse();
	
	long countByGender(String gender);
	
	long countByAgeLessThan(int age);
	
	long countByAgeBetween(int start,int end);
	
	long countByAgeGreaterThan(int age);
	
	
	
	long countByRegistrationDate(LocalDate date);
	

}
