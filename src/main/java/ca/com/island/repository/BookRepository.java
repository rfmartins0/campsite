package ca.com.island.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import ca.com.island.domain.BookEntity;
import ca.com.island.domain.LocalEntity;

public interface BookRepository extends CrudRepository<BookEntity, Long> {

	@Query("select u from BookEntity u where u.endDate >= :dateStart and u.startDate <= :dateEnd")
	public List<BookEntity> findFullLocals(@Param("dateStart") LocalDate dateStart, @Param("dateEnd") LocalDate dateEnd);

	public long deleteByReservationNumber(String reservationNumber);

	public Optional<BookEntity> findByReservationNumber(String reservationNumber);

	@Modifying
	@Query("update BookEntity u set u.startDate = :dateStart, u.endDate = :dateEnd, u.local =:localEntity where u.reservationNumber = :reservationNumber")
	public int setDatesById(@Param("dateStart") LocalDate dateStart, @Param("dateEnd") LocalDate dateEnd, @Param("reservationNumber") String reservationNumber, @Param("localEntity") LocalEntity localEntity);

}
