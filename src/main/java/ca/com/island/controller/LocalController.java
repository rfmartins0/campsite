package ca.com.island.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.com.island.dto.LocalDto;
import ca.com.island.service.BookService;
import ca.com.island.service.LocalService;
import io.swagger.v3.oas.annotations.Operation;


@RestController
public class LocalController {
	
	@Autowired
	private LocalService localService;
	
	@Autowired
	private BookService bookService;
	
    @Operation(summary = "Show Locals")
	@GetMapping(path = "/locals", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<LocalDto>> getAll() {
		return ResponseEntity.ok(localService.getAll());
	}
    
    @Operation(summary = "Show Free Sites")
  	@GetMapping(path = "/locals/free", produces = MediaType.APPLICATION_JSON_VALUE)
  	public ResponseEntity<List<LocalDto>> show(@RequestParam final String startDate, final String endDate) {
  		LocalDate dateStartDate = LocalDate.parse(startDate);
  		LocalDate dateEndDate = LocalDate.parse(endDate);
  		List<LocalDto> localDtoList = bookService.showAvailableDto(dateStartDate, dateEndDate);
  		return ResponseEntity.ok(localDtoList);
  	}

}
