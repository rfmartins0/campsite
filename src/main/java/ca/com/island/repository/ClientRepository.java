package ca.com.island.repository;

import org.springframework.data.repository.CrudRepository;

import ca.com.island.domain.ClientEntity;

public interface ClientRepository extends CrudRepository<ClientEntity, Long>{

}


