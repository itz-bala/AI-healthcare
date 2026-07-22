package main.Service;

import java.util.List;

import jakarta.validation.Valid;
import main.Dto.LabAnalyticsDto;
import main.Dto.LabRequestDto;
import main.Dto.LabResponseDto;

public interface LabService {

	LabResponseDto createLabTest(@Valid LabRequestDto dto);

	LabResponseDto getbyid(Long id);

	

	List<LabResponseDto> getAll();

	LabAnalyticsDto getAnalytics();

	List<LabResponseDto> getAllPatientLabReports(Long patientId);

}
