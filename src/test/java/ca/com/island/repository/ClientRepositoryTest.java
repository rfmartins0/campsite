package ca.com.island.repository;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import ca.com.island.domain.ClientEntity;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ClientRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private ClientRepository clientRepository;

	@Test
	public void testIdPersistence() {
		final String email = "franco@teste.com";
		ClientEntity clientEntity = new ClientEntity();
		clientEntity.setEmail(email);
		clientEntity = this.entityManager.persistAndFlush(clientEntity);
		assertTrue(this.clientRepository.findById(clientEntity.getId()).isPresent());
	}


}
