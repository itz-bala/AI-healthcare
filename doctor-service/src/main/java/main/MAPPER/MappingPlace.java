package main.MAPPER;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import main.Dto.DoctorRequestDto;
import main.Dto.DoctorResponseDto;
import main.Entity.Doctor;

@Component
public class MappingPlace {
	
	
	@Autowired
	private ModelMapper modelmapper;
	
	
	
	//dto to entity convert
	
	public Doctor toEntity(DoctorRequestDto dto) {
		return modelmapper.map(dto, Doctor.class);
	}
	
	//entity to dto convert
	
	
	public DoctorResponseDto toDto(Doctor doctor) {
		
		return modelmapper.map(doctor, DoctorResponseDto.class);
		
	}

}
