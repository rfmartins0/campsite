package ca.com.island.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import ca.com.island.dto.ClientBookDto;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class BookControllerTest {
	
	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void testShowAvailable() throws URISyntaxException {
		final String baseUrl = "http://localhost:" + port + "/books?startDate=" + LocalDate.now().toString() + "&endDate=" + LocalDate.now().plusDays(2l).toString();
		URI uri = new URI(baseUrl);
		ResponseEntity<Object> response = this.restTemplate.getForEntity(uri, Object.class);
		assertTrue(response.getStatusCode().is2xxSuccessful());
	}
	
	@Test
	public void testBookingCode() throws URISyntaxException {
		final String baseUrl = "http://localhost:" + port + "/books";
		URI uri = new URI(baseUrl);
		ClientBookDto clientBookDto = new ClientBookDto();
		clientBookDto.setEmail(baseUrl);
		clientBookDto.setEndDate(LocalDate.now().plusDays(2l));
		clientBookDto.setStartDate(LocalDate.now().plusDays(200l));
		clientBookDto.setName("Neil XYZ");
		HttpEntity<ClientBookDto> request = new HttpEntity<>(clientBookDto);
		ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);
		assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
	}
	
	@Test
	public void testBookingMessage() throws URISyntaxException {
		final String baseUrl = "http://localhost:" + port + "/books";
		URI uri = new URI(baseUrl);
		ClientBookDto clientBookDto = new ClientBookDto();
		clientBookDto.setEmail(baseUrl);
		clientBookDto.setEndDate(LocalDate.now().plusDays(2l));
		clientBookDto.setStartDate(LocalDate.now().plusDays(200l));
		clientBookDto.setName("Neil XYZ");
		HttpEntity<ClientBookDto> request = new HttpEntity<>(clientBookDto);
		ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);
		assertEquals("{\"message\":\"Invalid date\"}", result.getBody());
	}

}
