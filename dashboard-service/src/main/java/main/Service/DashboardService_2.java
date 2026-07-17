package main.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import main.Dto.AdminDashboardDto;
import main.Dto.AppointmentAnalyticsDto;
import main.Dto.BillingAnalyticsDto;
import main.Dto.DepartmentAnalyticsDto;
import main.Dto.DoctorAnalyticsDto;
import main.Dto.LabAnalyticsDto;
import main.Dto.PatientAnalyticsDto;
import main.Dto.PrescriptionAnalyticsDto;
import main.Dto.TrendAnalyticsDto;
import main.FeignClient.AppointmentClient;
import main.FeignClient.BillingClient;
import main.FeignClient.DoctorClient;
import main.FeignClient.LabClient;
import main.FeignClient.PatientClient;
import main.FeignClient.PrescriptionClient;

@Service
public class DashboardService_2 implements DashboardService{
	
	private PatientClient patientclient;
	
	private DoctorClient doctorclient;
	
	private AppointmentClient appointmentclient;
	
	private LabClient labclient;
	
	private PrescriptionClient prescriptionclient;
	
	private BillingClient billingclient;
	
	
	public DashboardService_2 (PatientClient patientclient,
	
	                           DoctorClient doctorclient,
	
	                           AppointmentClient appointmentclient,
	
	                                        LabClient labclient,
	
	                                     PrescriptionClient prescriptionclient,
	
	                                    BillingClient billingclient) {
		
		
		
		 this.patientclient=patientclient;
		 this.doctorclient=doctorclient;
		 this.appointmentclient=appointmentclient;
		 this.labclient=labclient;
		 this.prescriptionclient=prescriptionclient;
		 this.billingclient=billingclient;
		
	}
	
	
//admin dashboard
	@Override
	public AdminDashboardDto adminDashboard() {
		
		
		
		 AdminDashboardDto dto = new AdminDashboardDto();

		    Long totalPatients = patientclient.getTotalPatients();
		    
		    

	    Long activePatients = patientclient.getActivePatients();
	    
	    

		    Long totalDoctors = doctorclient.getTotalDoctors();
		    		
		    		
		    		
		    Long activeDoctors = doctorclient.getActiveDoctor();
		    
		    
		    
		    

		    Long totalAppointments = appointmentclient.getTotalAppointments();
		    		
		    		
		    		

		    Long todayAppointments = appointmentclient.getTodayAppointments();
		    		
		    		
		    		
		    		

		    Long completedAppointments =appointmentclient.getCompletedAppointments();

		    Long cancelledAppointments = appointmentclient.getCancelledAppointments();


		    
		   
		    BillingAnalyticsDto billing = billingclient.getBillingAnalytics();

		    dto.setTotalPatients(totalPatients);
		    dto.setActivePatients(activePatients);
		    dto.setTotalDoctors(totalDoctors);
		    dto.setActiveDoctors(activeDoctors);
		    dto.setTotalAppointments(totalAppointments);
		    dto.setTodayAppointments(todayAppointments);
		    dto.setCompletedAppointments(completedAppointments);
		    dto.setCancelledAppointments(cancelledAppointments);

		    dto.setTotalRevenue(billing.getTotalRevenue());
		    dto.setPendingPayments(billing.getPendingPayments());

		    return dto;
		
		
		
		
		
		
	}
	
	
	//appointments analytics

	@Override
	public AppointmentAnalyticsDto appointmentAnalytics() {
		// TODO Auto-generated method stub
		
		

	    return  appointmentclient.getAppointmentAnalytics();
		
		
		
	}
	
	

	
	//billing analytics
	
	
	@Override
	public BillingAnalyticsDto getRevenueAnalytics() {
		// TODO Auto-generated method stub
		
		
		 

		    return billingclient.getBillingAnalytics();
		
		
		
	
	}
	
	//doctor analytics

	@Override
	public DoctorAnalyticsDto doctorAnalytics(Long id) {
		// TODO Auto-generated method stub
		
	

		    return doctorclient.getDoctorAnalytics(id);
		
		
		
		
	}
	
	
	//patient analytics

	@Override
	public PatientAnalyticsDto patientAnalytics() {
		// TODO Auto-generated method stub
		
		 

		    return patientclient.getPatientAnalytics();
		
		
		
	}
	
	
	//prescription Analytics

	@Override
	public PrescriptionAnalyticsDto prescriptionAnalytics() {
		// TODO Auto-generated method stub
		
		

		    return prescriptionclient.getPrescriptionAnalytics();
		
		
	}
	
	
	//lab analytics

	@Override
	public LabAnalyticsDto getLabAnalytics() {
		// TODO Auto-generated method stub
		
		 

		    return labclient.getLabAnalytics();
		
		
		
	}

	//department analytic 

	@Override
	public DepartmentAnalyticsDto getDepartmentAnalytics() {
		// TODO Auto-generated method stub
		
		DepartmentAnalyticsDto dto = new DepartmentAnalyticsDto();

	    dto.setDoctorsCount(
	            doctorclient.getDoctorsByDepartment());

	    dto.setPatientsCount(
	            patientclient.getPatientsByDepartment());

	    dto.setAppointmentsCount(
	            appointmentclient.getAppointmentsByDepartment());

	    dto.setRevenueGenerated(
	            billingclient.getRevenueByDepartment());

	    return dto;
		
	
	}

	
	
	
    //trends
	
	@Override
	public TrendAnalyticsDto trendAnalytics() {
		// TODO Auto-generated method stub
		
		
		TrendAnalyticsDto dto = new TrendAnalyticsDto();

	    dto.setLast7DaysAppointments(
	            appointmentclient.last7DaysAppointments());

	    dto.setLast30DaysRevenue(
	            billingclient.last30DaysRevenue());

	    dto.setMonthlyPatientGrowth(
	            patientclient.monthlyGrowth());

	    Map<String, Double> performance = new HashMap<>();

	    performance.put("Revenue",
	            billingclient.getBillingAnalytics().getTotalRevenue());

	    performance.put("Patients",
	            patientclient.getTotalPatients().doubleValue());

	    performance.put("Doctors",
	            doctorclient.getTotalDoctors().doubleValue());

	    performance.put("Appointments",
	            appointmentclient.getTotalAppointments().doubleValue());

	    dto.setOverallHospitalPerformance(performance);

	    return dto;
		
		
	}

	
	
	  //csv file 

	@Override
	public byte[] exportCsv() {
		// TODO Auto-generated method stub
		
		
		StringBuilder csv=new StringBuilder();
		
		  // Get billing analytics
	    BillingAnalyticsDto billing = billingclient.getBillingAnalytics();

		
		
		csv.append("dashboard-service-Report\n\n");
		
		DateTimeFormatter formater=DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		
		csv.append("Generated Time:").append(LocalDateTime.now().format(formater)).append("\n\n");
		
		
		csv.append("Total patients:").append(patientclient.getTotalPatients()).append("\n");
		
		csv.append("Active Patients:").append(patientclient.getActivePatients()).append("\n");
		
		csv.append("Total doctors:").append(doctorclient.getTotalDoctors()).append("\n");
		
		csv.append("Active doctorsn:").append(doctorclient.getActiveDoctor()).append("\n");
		
		csv.append("Total Appointments:").append(appointmentclient.getTotalAppointments()).append("\n");
		
		csv.append("Today appointments:").append(appointmentclient.getTodayAppointments()).append("\n");
		
		csv.append("Completed Appointments:").append(appointmentclient.getCompletedAppointments()).append("\n");
		
		csv.append("Cancelled Appointments:").append(appointmentclient.getCancelledAppointments()).append("\n");
		
		csv.append("TotaRevenue:").append(billing.getTotalRevenue()).append("\n");
		csv.append("Pendind Revenue:").append(billing.getPendingPayments());
		
		
		
		return csv.toString().getBytes();
	}


	
	//excel
	
	
	@Override
	public byte[] exportExcel() {
		// TODO Auto-generated method stub
		
		 AdminDashboardDto dto = adminDashboard();

	        try (Workbook workbook = new XSSFWorkbook();
	             ByteArrayOutputStream out = new ByteArrayOutputStream()) {

	            Sheet sheet = workbook.createSheet("Dashboard");

	            Map<String, String> data = new LinkedHashMap<>();

	            data.put("Total Patients", String.valueOf(dto.getTotalPatients()));
	            data.put("Active Patients", String.valueOf(dto.getActivePatients()));
	            data.put("Total Doctors", String.valueOf(dto.getTotalDoctors()));
	            data.put("Active Doctors", String.valueOf(dto.getActiveDoctors()));
	            data.put("Total Appointments", String.valueOf(dto.getTotalAppointments()));
	            data.put("Today's Appointments", String.valueOf(dto.getTodayAppointments()));
	            data.put("Completed Appointments", String.valueOf(dto.getCompletedAppointments()));
	            data.put("Cancelled Appointments", String.valueOf(dto.getCancelledAppointments()));
	            data.put("Total Revenue", String.valueOf(dto.getTotalRevenue()));
	            data.put("Pending Payments", String.valueOf(dto.getPendingPayments()));

	            int rowNum = 0;

	            for (Map.Entry<String, String> entry : data.entrySet()) {

	                Row row = sheet.createRow(rowNum++);

	                row.createCell(0).setCellValue(entry.getKey());
	                row.createCell(1).setCellValue(entry.getValue());
	            }

	            sheet.autoSizeColumn(0);
	            sheet.autoSizeColumn(1);

	            workbook.write(out);

	            return out.toByteArray();

	        } catch (IOException e) {
	            throw new RuntimeException("Error while generating Excel", e);
	        }
	    }


	
	
	
	//pdf
	
	@Override
	public byte[] exportPdf() {
		// TODO Auto-generated method stub
		AdminDashboardDto dto = adminDashboard();

	    try {

	        Document document = new Document();
	        ByteArrayOutputStream out = new ByteArrayOutputStream();

	        PdfWriter.getInstance(document, out);

	        document.open();

	        document.add(new Paragraph("Hospital Dashboard Report"));
	        document.add(new Paragraph(" "));

	        document.add(new Paragraph("Total Patients : " + dto.getTotalPatients()));
	        document.add(new Paragraph("Active Patients : " + dto.getActivePatients()));
	        document.add(new Paragraph("Total Doctors : " + dto.getTotalDoctors()));
	        document.add(new Paragraph("Active Doctors : " + dto.getActiveDoctors()));
	        document.add(new Paragraph("Total Appointments : " + dto.getTotalAppointments()));
	        document.add(new Paragraph("Today's Appointments : " + dto.getTodayAppointments()));
	        document.add(new Paragraph("Completed Appointments : " + dto.getCompletedAppointments()));
	        document.add(new Paragraph("Cancelled Appointments : " + dto.getCancelledAppointments()));
	        document.add(new Paragraph("Total Revenue : " + dto.getTotalRevenue()));
	        document.add(new Paragraph("Pending Payments : " + dto.getPendingPayments()));

	        document.close();

	        return out.toByteArray();

	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}

	    
		
	


	

	


}
