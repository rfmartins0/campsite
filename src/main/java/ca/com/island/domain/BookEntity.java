package ca.com.island.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "books")
public class BookEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column(name = "startDate")
	private LocalDate startDate;
	
	@Column(name = "endDate")
	private LocalDate endDate;

	@ManyToOne
	private LocalEntity local;
	
	@ManyToOne
	private ClientEntity client;
	
	@Column
	private String reservationNumber;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public LocalEntity getLocal() {
		return local;
	}

	public void setLocal(LocalEntity local) {
		this.local = local;
	}

	public ClientEntity getClient() {
		return client;
	}

	public void setClient(ClientEntity client) {
		this.client = client;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public String getReservationNumber() {
		return reservationNumber;
	}

	public void setReservationNumber(String reservationNumber) {
		this.reservationNumber = reservationNumber;
	}

}
