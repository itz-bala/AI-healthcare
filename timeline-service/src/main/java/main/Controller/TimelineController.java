package main.Controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import main.Dto.TimelineResponseDto;
import main.Enum.TimelineStatus;
import main.Service.TimelineService;

@RestController
@RequestMapping("timeline")
public class TimelineController {
	
	
	@Autowired
	private TimelineService service;
	
	
	
	@GetMapping("/patient/{patientId}")
	public ResponseEntity<TimelineResponseDto>getTimelineResponse(@PathVariable Long patientId,
			                                          @RequestParam(required=false)LocalDate fromDate,
			                                          @RequestParam(required=false)LocalDate toDate,
			                                 @RequestParam(required=false)String type,
			                                 @RequestParam(defaultValue = "desc")String sort,
			                                 @RequestParam(defaultValue="0")int page,
			                                 @RequestParam(defaultValue="10")int size){
		
		
		TimelineResponseDto dto=service.getTimelineResponse( patientId,
                                                             fromDate,
                                                              toDate,
                                                              type,
                                                               sort,
                                                               page,
                                                               size);

		
		return ResponseEntity.ok(dto);
		
	}


}
