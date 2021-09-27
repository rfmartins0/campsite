package ca.com.island.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.com.island.domain.ClientEntity;
import ca.com.island.dto.ClientDto;
import ca.com.island.repository.ClientRepository;
import ca.com.island.service.converter.ClientRequestConverter;
import ca.com.island.service.converter.ClientResponseConverter;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private ClientResponseConverter clientResponseConverter;
	
	@Autowired
	private ClientRequestConverter clientRequestConverter;

	public ClientDto save(ClientDto clientDto) {
		ClientEntity clientEntity = this.clientRepository.save(clientRequestConverter.apply(clientDto));
		return this.clientResponseConverter.apply(clientEntity);		
	}
	
	public ClientEntity saveEntity(ClientDto clientDto) {
		return this.clientRepository.save(clientRequestConverter.apply(clientDto));		
	}

	public List<ClientDto> getAll() {
		final List<ClientEntity> clientEntitys = (List<ClientEntity>) this.clientRepository.findAll();
		return clientResponseConverter.applyList(clientEntitys);
	}
	
	public ClientDto getClientById(Long id) {
		ClientEntity clientEntity = clientRepository.findById(id).get();
		return clientResponseConverter.apply(clientEntity);
	}
	
	public ClientEntity getClientEntityById(Long id) {
		ClientEntity clientEntity = clientRepository.findById(id).get();
		return clientEntity;
	}
	

}
