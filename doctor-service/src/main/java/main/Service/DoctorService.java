package main.Service;

import java.util.List;
import java.util.Map;

import jakarta.validation.Valid;
import main.Dto.DoctorAnalyticsDto;
import main.Dto.DoctorRequestDto;
import main.Dto.DoctorResponseDto;
import main.Entity.Department;

public interface DoctorService {

	DoctorResponseDto createDoctor(@Valid DoctorRequestDto dto);

	DoctorResponseDto getbyid(Long id);

	List<DoctorResponseDto> getAll();

	List<DoctorResponseDto> getSpecialists(Department specialist);

	

	Long countDoctors();

	Long getActiveDoctorsCount();

	Long getInactiveDoctorsCount();

	void updateActiveToInactive(Long id);

	void updateInactiveToActive(Long id);

	DoctorAnalyticsDto doctorAnalytics(Long id);

	Map<Department, Long> getDoctorDepartmentAnalytics();

	

	

}
