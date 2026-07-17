package main.Dto;

import java.util.Map;

import lombok.Data;

@Data
public class BillingAnalyticsDto {
	
	

    
    
    
    
    private double dailyRevenue;

    private double weeklyRevenue;

    private double monthlyRevenue;
    


    private Double totalRevenue;
    private Double pendingPayments;
    
    private Map<String, Double> revenueByDoctor;

    private Map<String, Double> revenueByDepartment;

    private Map<String, Double> revenueByPaymentMethod; 


}
