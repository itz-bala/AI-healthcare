package main.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import main.Dto.AdminDashboardDto;
import main.Dto.AppointmentAnalyticsDto;
import main.Dto.BillingAnalyticsDto;
import main.Dto.DepartmentAnalyticsDto;
import main.Dto.DoctorAnalyticsDto;
import main.Dto.LabAnalyticsDto;
import main.Dto.PatientAnalyticsDto;
import main.Dto.PrescriptionAnalyticsDto;
import main.Dto.TrendAnalyticsDto;
import main.Service.DashboardService;

@RestController
@RequestMapping("/dashboard")
class DashboardController {
	
	
	@Autowired
	private DashboardService service;
	
	
	
	//admin
	  @GetMapping("/admin")
	    public ResponseEntity<AdminDashboardDto> adminDashboard() {
		  
		  AdminDashboardDto dto=service.adminDashboard();
	        return ResponseEntity.ok(dto);
	    }
	  
	  
	  
	  //appointments
	  
	  @GetMapping("/AppointmentsAnalytics")
	    public ResponseEntity<AppointmentAnalyticsDto> appointmentAnalytics() {
		  
		  AppointmentAnalyticsDto dto=service.appointmentAnalytics();
		  
	        return ResponseEntity.ok(dto);
	    }
	  
	  
	  //revenue
	
	  @GetMapping("/BillingAnalytics")
	    public ResponseEntity<BillingAnalyticsDto> revenueAnalytics() {
		  
		  BillingAnalyticsDto dto=service.getRevenueAnalytics();
		  
	        return ResponseEntity.ok(dto);
	    }
	  
	  
	  //doctor
	  
	  @GetMapping("/DoctorAnalytics/{id}")
	    public ResponseEntity<DoctorAnalyticsDto> doctorAnalytics(@PathVariable Long id) {
		  
		  DoctorAnalyticsDto dto=service.doctorAnalytics(id);
		  
	        return ResponseEntity.ok(dto);
	    }
	  
	  //patient
	  
	  @GetMapping("/PatientAnalytics")
	    public ResponseEntity<PatientAnalyticsDto> patientAnalytics() {
		  
		  PatientAnalyticsDto dto=service.patientAnalytics();
	        return ResponseEntity.ok(dto);
	    }
	  
	  //prescription
	  
	  @GetMapping("/PrescriptionAnalytics")
	    public ResponseEntity<PrescriptionAnalyticsDto> prescriptionAnalytics() {
		  
		  PrescriptionAnalyticsDto dto=service.prescriptionAnalytics();
	        return ResponseEntity.ok(dto);
	    }
	  
	  
	  
	  //lab
	  @GetMapping("/LabAnalytics")
	    public ResponseEntity<LabAnalyticsDto> labAnalytics() {
		  
		  LabAnalyticsDto dto=service.getLabAnalytics();
	        return ResponseEntity.ok(dto);
	    }
	  
	  //departments
	  
	  @GetMapping("/DepartmentAnalytics")
	    public ResponseEntity<DepartmentAnalyticsDto> departmentAnalytics() {
		  
		  DepartmentAnalyticsDto dto=service.getDepartmentAnalytics();
	        return ResponseEntity.ok(dto);
	    }
	  
	  //trends
	  
	  @GetMapping("/TrendsAnalytics")
	    public ResponseEntity<TrendAnalyticsDto> trendAnalytics() {
		  
		  TrendAnalyticsDto dto=service.trendAnalytics();
	        return ResponseEntity.ok(dto);
	    }
	  
	  
	  
	  //csv file
	  
	  
	  @GetMapping("/admin/export/csv")
	  public ResponseEntity<byte[]> exportCsv() {

	      byte[] csv = service.exportCsv();

	      return ResponseEntity.ok()
	              .header(HttpHeaders.CONTENT_DISPOSITION,
	                      "attachment; filename=dashboard-report.csv")
	              .contentType(MediaType.parseMediaType("text/csv"))
	              .body(csv);
	  }
	  
	  
	  //excel
	  
	  @GetMapping("/admin/export/excel")
	  public ResponseEntity<byte[]> exportExcel() {

      byte[] excel = service.exportExcel();

	      return ResponseEntity.ok()
	              .header(HttpHeaders.CONTENT_DISPOSITION,
	                      "attachment; filename=dashboard-report.xlsx")
	              .contentType(MediaType.parseMediaType(
	                      "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
	              .body(excel);
	  }
	  
	  
	  
	  
	  
	 
	  
	  //pdf
	  
	  @GetMapping("/admin/export/pdf")
	  public ResponseEntity<byte[]> exportPdf() {

	      byte[] pdf = service.exportPdf();

	      return ResponseEntity.ok()
	              .header(HttpHeaders.CONTENT_DISPOSITION,
	                      "attachment; filename=dashboard-report.pdf")
	              .contentType(MediaType.APPLICATION_PDF)
	              .body(pdf);
	  }
}
