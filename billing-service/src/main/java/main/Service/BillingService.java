package main.Service;

import java.util.List;
import java.util.Map;

import jakarta.validation.Valid;
import main.Dto.BillingAnalyticsDto;
import main.Dto.BillingRequestDto;
import main.Dto.BillingResponseDto;
import main.Entity.Department;

public interface BillingService {

	BillingResponseDto createBilling(@Valid BillingRequestDto dto);

	BillingResponseDto getbyid(Long id);

	List<BillingResponseDto> getAll();

	BillingAnalyticsDto getBillingAnalytics();

	Map<Department, Long> getBillingDepartmentAnalytics();

	Map<String, Double> last30DaysRevenue();

	

	Map<String, Double> getRevenueByDepartment();

	List<BillingResponseDto> getPatientBill(Long patientId);



}
