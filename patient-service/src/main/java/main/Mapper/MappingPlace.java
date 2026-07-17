package main.Mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import main.Dto.PatientRequestDto;
import main.Dto.PatientResponseDto;
import main.Entity.Patient;

@Component
public class MappingPlace {
	
	
	
	   @Autowired
	   private ModelMapper modelmapper;
	   
	   
	   
	   //dto to entity convert
	   
	   
	   public Patient toEntity(PatientRequestDto dto) {
		   
		   return modelmapper.map(dto,Patient.class);
	   }
	   
	   
	   //entity to dto convert
	   
	   
	   public PatientResponseDto toDto(Patient patient) {
		   
		   return modelmapper.map(patient,PatientResponseDto.class );
	   }

}
