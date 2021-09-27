package ca.com.island.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.com.island.domain.BookEntity;
import ca.com.island.domain.ClientEntity;
import ca.com.island.domain.LocalEntity;
import ca.com.island.dto.ClientBookDto;
import ca.com.island.dto.ClientDto;
import ca.com.island.dto.LocalDto;
import ca.com.island.exception.ValidateException;
import ca.com.island.repository.BookRepository;
import ca.com.island.service.converter.LocalResponseConverter;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private ClientService clientService;

	@Autowired
	private LocalService localService;

	@Autowired
	private LocalResponseConverter localResponseConverter;

	@Transactional
	public String save(ClientBookDto clientBookDto) {
		List<LocalEntity> availables = showAvailable(clientBookDto.getStartDate(), clientBookDto.getEndDate());
		if (!availables.isEmpty()) {
			ClientDto clientDto = new ClientDto();
			clientDto.setEmail(clientBookDto.getEmail());
			clientDto.setName(clientBookDto.getName());
			ClientEntity clientyEntity = clientService.saveEntity(clientDto);
			BookEntity bookEntity = new BookEntity();
			bookEntity.setStartDate(clientBookDto.getStartDate());
			bookEntity.setEndDate(clientBookDto.getEndDate());
			bookEntity.setClient(clientyEntity);
			bookEntity.setLocal(availables.iterator().next());
			String uuid = UUID.randomUUID().toString();
			bookEntity.setReservationNumber(uuid);
			bookRepository.save(bookEntity);
			return uuid;
		}
		throw new ValidateException("Hotel site occupied");
	}

	@Transactional
	public boolean cancelReservation(final String numberOfReservation) {
		long number = bookRepository.deleteByReservationNumber(numberOfReservation);
		return number > 0;
	}

	@Transactional
	public boolean updateReservation(final LocalDate dateStart, final LocalDate dateEnd,
			final String numberOfReservation) {
		int number = 0;
		Optional<BookEntity> bookEntityOptional = bookRepository.findByReservationNumber(numberOfReservation);
		if (!bookEntityOptional.isPresent()) {
			throw new ValidateException("The reservation number is wrong");
		}
		List<LocalEntity> localEntitys = showAvailableWithOut(dateStart, dateEnd, numberOfReservation);
		if (!localEntitys.isEmpty()) {
			LocalEntity localEntity = localEntitys.iterator().next();
			number = bookRepository.setDatesById(dateStart, dateEnd, numberOfReservation, localEntity);
		}
		return number > 0;
	}

	public List<LocalEntity> showAvailable(LocalDate startDate, LocalDate endDate) {
		List<BookEntity> bookEntitys = bookRepository.findFullLocals(startDate, endDate);
		List<LocalEntity> localEntitys = List.copyOf(localService.getEntityAll());
		List<LocalEntity> localAvailablesEntitys = new LinkedList<LocalEntity>(localService.getEntityAll());
		for (LocalEntity localEntity : localEntitys) {
			for (BookEntity bookEntity : bookEntitys) {
				if (localEntity.getId() == bookEntity.getLocal().getId()) {
					localAvailablesEntitys.remove(localEntity);
				}
			}
		}
		return localAvailablesEntitys;
	}

	public List<LocalEntity> showAvailableWithOut(LocalDate startDate, LocalDate endDate, String reserved) {
		List<BookEntity> bookFullEntitys = bookRepository.findFullLocals(startDate, endDate);
		List<BookEntity> bookEntitys = new ArrayList<>();
		for (BookEntity bookFullEntity : bookFullEntitys) {
			if (!bookFullEntity.getReservationNumber().equals(reserved)) {
				bookEntitys.add(bookFullEntity);
			}
		}
		List<LocalEntity> localEntitys = List.copyOf(localService.getEntityAll());
		List<LocalEntity> localAvailablesEntitys = new LinkedList<LocalEntity>(localService.getEntityAll());
		for (LocalEntity localEntity : localEntitys) {
			for (BookEntity bookEntity : bookEntitys) {
				if (localEntity.getId() == bookEntity.getLocal().getId()) {
					localAvailablesEntitys.remove(localEntity);
				}
			}
		}
		return localAvailablesEntitys;
	}

	public List<LocalDto> showAvailableDto(LocalDate startDate, LocalDate endDate) {
		return this.localResponseConverter.applyList(this.showAvailable(startDate, endDate));
	}


}
