package main.Dto;

import java.util.List;

import lombok.Data;

@Data
public class TimelineResponseDto {
	
	
	
	    private List<TimelineItemDto> records;

	    private int currentPage;

	    private int totalPages;

	    private long totalElements;


}
