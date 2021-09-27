package ca.com.island.repository;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import ca.com.island.domain.LocalEntity;

@RunWith(SpringRunner.class)
@DataJpaTest
public class LocalRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private LocalRepository localRepository;

	@Test
	public void testIdPersistence() {
		LocalEntity localEntity = new LocalEntity();
		localEntity = this.entityManager.persistAndFlush(localEntity);
		assertTrue(this.localRepository.findById(localEntity.getId()).isPresent());
	}


}
