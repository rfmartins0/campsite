package ca.com.island.service.converter;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import ca.com.island.domain.LocalEntity;
import ca.com.island.dto.LocalDto;

@Component
public class LocalResponseConverter implements Function<LocalEntity, LocalDto> {

	@Override
	public LocalDto apply(LocalEntity localEntity) {
		LocalDto localDto = new LocalDto();
		localDto.setLocal(localEntity.getSite());
		return localDto;
	}

	public List<LocalDto> applyList(List<LocalEntity> localEntitys) {
		List<LocalDto> localDtos = new LinkedList<>();
		for (LocalEntity localEntity : localEntitys) {
			localDtos.add(apply(localEntity));
		}
		return localDtos;
	}

}
