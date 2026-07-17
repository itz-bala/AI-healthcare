package main.Mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import main.Dto.LabRequestDto;
import main.Dto.LabResponseDto;
import main.Entity.Lab;

@Component
public class MappingPlace {
	
	
	@Autowired
	private ModelMapper modelmapper;
	
	
	//dto to entity convert
	
	
	public Lab toEntity(LabRequestDto dto) {
		
		return modelmapper.map(dto,Lab.class);
	}
	
	
	
	//entity to dto convert
	
	
	public LabResponseDto toDto(Lab labtest) {
		
		return modelmapper.map(labtest, LabResponseDto.class);
	}
	
	
	
	
	

}
