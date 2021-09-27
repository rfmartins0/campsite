package ca.com.island.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.com.island.domain.LocalEntity;
import ca.com.island.dto.LocalDto;
import ca.com.island.repository.LocalRepository;
import ca.com.island.service.converter.LocalResponseConverter;

@Service
public class LocalService {
	
	@Autowired
	private LocalRepository localRepository;
	
	@Autowired
	private LocalResponseConverter localResponseConverter;

	public List<LocalDto> getAll() {
		final List<LocalEntity> localEntitys = (List<LocalEntity>) this.localRepository.findAll();
		return localResponseConverter.applyList(localEntitys);
	}
	
	public List<LocalEntity> getEntityAll() {
		return (List<LocalEntity>) this.localRepository.findAll();
	}
	
	public LocalDto getLocalById(Long id) {
		LocalEntity localEntity = localRepository.findById(id).get();
		return localResponseConverter.apply(localEntity);
	}
	
	public LocalEntity getLocalEntityById(Long id) {
		LocalEntity localEntity = localRepository.findById(id).get();
		return localEntity;
	}
	
	public long getNumberOfLocals() {
		return localRepository.count();
	}
	

}
