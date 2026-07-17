package main.Mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import main.Dto.PrescriptionMedicineRequestDto;
import main.Dto.PrescriptionMedicineResponseDto;
import main.Dto.PrescriptionRequestDto;
import main.Dto.PrescriptionResponseDto;
import main.Entity.Prescription;
import main.Entity.PrescriptionMedicine;

@Component
public class MappingPlace {
	
	@Autowired
	private ModelMapper modelmapper;
	
	//Prescription.........................
	
	//dto to entity convert
	
	public Prescription toEntity(PrescriptionRequestDto dto) {
		
		
		return modelmapper.map(dto,Prescription.class);
	}
	
	
	//entity to dto convert
	
	
	public PrescriptionResponseDto toDto(Prescription prescription) {
		
		return modelmapper.map(prescription, PrescriptionResponseDto.class);
	}
	
	
	
	//PrescriptionMedicine...................
	
	
	//dto to entity convert
	
	public PrescriptionMedicine  medicine_toEntity(PrescriptionMedicineRequestDto dto) {
		
		return modelmapper.map(dto, PrescriptionMedicine.class);
	}
	
	//entity to dto convert
	
	public PrescriptionMedicineResponseDto medicine_toDto(PrescriptionMedicine prescriptionmedicine) {
		
		return modelmapper.map(prescriptionmedicine, PrescriptionMedicineResponseDto.class);
	}
	

}
