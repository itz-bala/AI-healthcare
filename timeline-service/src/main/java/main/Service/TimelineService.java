package main.Service;

import java.time.LocalDate;

import main.Dto.TimelineResponseDto;
import main.Enum.TimelineStatus;

public interface TimelineService {

	TimelineResponseDto getTimelineResponse(Long patientId, 
			                       LocalDate fromDate,
			                        LocalDate toDate, 
			                         String type,
			                         String sort,
			                         int page,
			                         int size);

}
