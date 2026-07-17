package main.Dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PrescriptionMedicineRequestDto {
	
	
    
	
	private String medicineName;
	
	private String  dosage;
	
	private String frequency;
	
	private Integer durationDays;

    private String instructions;
    
    @NotNull(message="must be medicinefees")
    private Double medicineFees;

}
