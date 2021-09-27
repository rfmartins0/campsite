package ca.com.island.controller;

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
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class BookControllerTest {
	
	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void testValidacaoSenhaCodigoRetorno() throws URISyntaxException {
		final String baseUrl = "http://localhost:" + port + "/books?startDate=" + LocalDate.now().toString() + "&endDate=" + LocalDate.now().plusDays(2l).toString();
		URI uri = new URI(baseUrl);
		ResponseEntity<Object> response = this.restTemplate.getForEntity(uri, Object.class);
		assertTrue(response.getStatusCode().is2xxSuccessful());
	}

}
