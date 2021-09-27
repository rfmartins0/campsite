package ca.com.island.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ca.com.island.dto.ClientDto;
import ca.com.island.service.ClientService;


@RestController
public class ClientController {
	
	@Autowired
	private ClientService clientService;
	
	
	@PostMapping(path = "/clients", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ClientDto> save(@RequestBody final ClientDto clientDto) {
		return new ResponseEntity<ClientDto>(clientService.save(clientDto), HttpStatus.ACCEPTED);
	}

}
