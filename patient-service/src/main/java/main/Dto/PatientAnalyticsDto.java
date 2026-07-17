package main.Dto;


import lombok.Data;
import main.Entity.Department;


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

	   private Department mostVisitedDepartment;

}
