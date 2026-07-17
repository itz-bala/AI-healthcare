package main.Service;

import main.Dto.AdminDashboardDto;
import main.Dto.AppointmentAnalyticsDto;
import main.Dto.DepartmentAnalyticsDto;
import main.Dto.DoctorAnalyticsDto;
import main.Dto.LabAnalyticsDto;
import main.Dto.PatientAnalyticsDto;
import main.Dto.PrescriptionAnalyticsDto;
import main.Dto.BillingAnalyticsDto;
import main.Dto.TrendAnalyticsDto;

public interface DashboardService {

	AdminDashboardDto adminDashboard();

	AppointmentAnalyticsDto appointmentAnalytics();

	BillingAnalyticsDto getRevenueAnalytics();

	DoctorAnalyticsDto doctorAnalytics(Long id);

	PatientAnalyticsDto patientAnalytics();

	PrescriptionAnalyticsDto prescriptionAnalytics();

	LabAnalyticsDto getLabAnalytics();

	DepartmentAnalyticsDto getDepartmentAnalytics();

	TrendAnalyticsDto trendAnalytics();

	byte[] exportCsv();

	byte[] exportExcel();

	byte[] exportPdf();

}
