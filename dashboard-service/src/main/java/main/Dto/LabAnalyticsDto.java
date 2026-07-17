package main.Dto;

import java.util.Map;

import lombok.Data;

@Data
public class LabAnalyticsDto {
	
	 private long totalLabTests;

	    private long pendingReports;

	    private long completedReports;
	    
	    private Map<String,Long> requestedLabTests;

	    private Map<String, Long> mostRequestedLabTests;


}
