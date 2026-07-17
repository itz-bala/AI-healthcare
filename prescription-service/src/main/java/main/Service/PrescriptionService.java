package main.Service;

import java.util.List;

import jakarta.validation.Valid;
import main.Dto.PrescriptionAnalyticsDto;
import main.Dto.PrescriptionRequestDto;
import main.Dto.PrescriptionResponseDto;

public interface PrescriptionService {

	PrescriptionResponseDto createPrescription(@Valid PrescriptionRequestDto dto);

	PrescriptionResponseDto getbyid(Long id);

	List<PrescriptionResponseDto> getall();

	PrescriptionAnalyticsDto analytics();

	

}
