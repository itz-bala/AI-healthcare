package main.Mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import main.Dto.BillingRequestDto;
import main.Dto.BillingResponseDto;
import main.Entity.Billing;

@Component
public class ModelConfig {
	
	
	@Autowired
	private ModelMapper modelmapper;
	
	
	//dto to entity convert
	public Billing toEntity(BillingRequestDto dto) {
		
		
		return modelmapper.map(dto, Billing.class);
	}
	
	
	//entity to  dto convert
	public BillingResponseDto toDto(Billing billing) {
		
		return modelmapper.map(billing, BillingResponseDto.class);
	}
	
	
	
	

}
