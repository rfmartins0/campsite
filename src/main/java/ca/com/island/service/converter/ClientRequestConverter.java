package ca.com.island.service.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import ca.com.island.domain.ClientEntity;
import ca.com.island.dto.ClientDto;

@Component
public class ClientRequestConverter implements Function<ClientDto, ClientEntity> {
	
	@Override
	public ClientEntity apply(ClientDto clientDto) {
		ClientEntity clientEntity = new ClientEntity();
		clientEntity.setName(clientDto.getName());
		clientEntity.setEmail(clientDto.getEmail());
		return clientEntity;
	}

}

