package main.Service;

import java.util.List;
import java.util.Map;

import jakarta.validation.Valid;
import main.Dto.PatientAnalyticsDto;
import main.Dto.PatientRequestDto;
import main.Dto.PatientResponseDto;
import main.Entity.Department;


public interface PatientService {

	PatientResponseDto createPatient(@Valid PatientRequestDto dto);

	PatientResponseDto getbyid(Long id);

	PatientAnalyticsDto getAnalytics();

	Long getPatientCount();

	Long getActivePatientCount();

	Long getInActivePatientsCount();

	void updateFirstVisit(Long id);

	List<PatientResponseDto> getAll();

	void  inactivePatient(Long id);

	void activePatients(Long id);

	

	Map<String, Long> monthlyGrowth();

	Map<Department, Long> getDepartmentWisePatients();

	//Map<Department, Long> getPatientDepartmentAnalytics();

	



	

}
