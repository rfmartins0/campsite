package ca.com.island.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.com.island.dto.LocalDto;
import ca.com.island.service.LocalService;
import io.swagger.v3.oas.annotations.Operation;


@RestController
public class LocalController {
	
	@Autowired
	private LocalService localService;
	
    @Operation(summary = "Show Locals")
	@GetMapping(path = "/locals", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<LocalDto>> getAll() {
		return ResponseEntity.ok(localService.getAll());
	}

}
