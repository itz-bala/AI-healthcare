package main.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import main.Entity.Doctor;
import main.Entity.Department;

public interface DoctorRepository extends JpaRepository<Doctor,Long>{
	
	
	boolean existsByEmail(String email);
	
	Optional<Doctor>findById(Long id);
	
	List<Doctor>findByDepartment(Department department);

	long count();
	
	long countByActiveTrue();
	long countByActiveFalse();
}
