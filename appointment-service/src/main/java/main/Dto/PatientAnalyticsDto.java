package main.Dto;

import lombok.Data;

@Data
public class PatientAnalyticsDto {
	
	 private long newPatients;

	    private long returningPatients;

	    private long malePatients;

	    private long femalePatients;

	    private long otherPatients;

	    private long children;

	    private long adults;

	    private long middleAge;

	    private long seniorCitizens;

	    private String mostVisitedDepartment;

}
