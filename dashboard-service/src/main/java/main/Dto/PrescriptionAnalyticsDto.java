package main.Dto;

import java.util.Map;

import lombok.Data;

@Data
public class PrescriptionAnalyticsDto {
	
	
	
	 private long totalPrescriptions;

	    private Map<String, Long> mostPrescribedMedicines;

	    private Map<String, Long> prescriptionsByDoctor;

	    private Map<String, Long> prescriptionsByDepartment;

}
