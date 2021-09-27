package ca.com.island.service.converter;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import ca.com.island.domain.BookEntity;
import ca.com.island.dto.BookDto;

@Component
public class BookResponseConverter implements Function<BookEntity, BookDto> {

	@Override
	public BookDto apply(BookEntity bookEntity) {
		BookDto bookDto = new BookDto();
		bookDto.setStartDate(bookEntity.getStartDate());
		bookDto.setEndDate(bookEntity.getEndDate());
		return bookDto;
	}

	public List<BookDto> applyList(List<BookEntity> bookEntitys) {
		List<BookDto> bookDtos = new LinkedList<>();
		for (BookEntity bookEntity : bookEntitys) {
			bookDtos.add(apply(bookEntity));
		}
		return bookDtos;
	}

}