package main.Service;

import java.util.List;
import java.util.Map;

import main.Dto.AppointmentAnalyticsDto;
import main.Dto.AppointmentRequestDto;
import main.Dto.AppointmentResponseDto;
import main.Dto.DoctorAnalyticsDto;
import main.Entity.Department;

public interface AppointmentService {

	AppointmentResponseDto createBooking(AppointmentRequestDto dto);

	AppointmentResponseDto getbyid(Long id);

	List<AppointmentResponseDto> getAll();

	AppointmentAnalyticsDto analytics();

	

	Long todayCount();

	Long getAllAppointmentscount();

	Long completedAppointments();

	Long cancelledAppointments();

	

	

	void updateToCancelled(Long id);

	void updateToCompleted(Long id);

	Map<Department, Long> getAppointmentDepartmentAnalytics();

	Map<String, Long> last7DaysAppointments();

	

	

}
