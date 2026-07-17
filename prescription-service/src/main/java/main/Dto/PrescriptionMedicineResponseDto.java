package main.Dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Data
public class PrescriptionMedicineResponseDto {
	
	
    private Long id;
	
	
	
	
	private String medicineName;
	
	private String  dosage;
	
	private String frequency;
	
	private Integer durationDays;

    private String instructions;

    private Double medicineFees;

}
