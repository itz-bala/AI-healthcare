package main.Dto;

import java.time.LocalDateTime;

import lombok.Data;
import main.Enum.TimelineStatus;

@Data
public class TimelineItemDto {
	
	private LocalDateTime eventDate;

    private String recordType;

    private String title;

    private String description;
    
    private TimelineStatus timelineStatus;

	

}
