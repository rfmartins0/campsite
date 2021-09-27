package ca.com.island.repository;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import ca.com.island.domain.BookEntity;
import ca.com.island.domain.ClientEntity;
import ca.com.island.domain.LocalEntity;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private BookRepository bookRepository;

	@Test
	public void testIdPersistence() {
		final String email = "franco@teste.com";
		BookEntity bookEntity = new BookEntity();
		bookEntity.setStartDate(LocalDate.MAX);
		bookEntity.setEndDate(LocalDate.MIN);
		LocalEntity localEntity = new LocalEntity();
		ClientEntity clientEntity = new ClientEntity();
		clientEntity.setEmail(email);
		bookEntity.setReservationNumber(UUID.randomUUID().toString());
		clientEntity = this.entityManager.persistAndFlush(clientEntity);
		localEntity = this.entityManager.persistAndFlush(localEntity);
		bookEntity.setClient(clientEntity);
		bookEntity.setLocal(localEntity);
		bookEntity = this.entityManager.persistAndFlush(bookEntity);
		assertTrue(this.bookRepository.findById(bookEntity.getId()).isPresent());
	}


}
