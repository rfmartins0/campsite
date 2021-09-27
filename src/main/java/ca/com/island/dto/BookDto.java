package ca.com.island.dto;

import java.time.LocalDate;

public class BookDto {

	private Long localId;

	private Long clientId;

	private LocalDate startDate;

	private LocalDate endDate;

	public Long getLocalId() {
		return localId;
	}

	public void setLocalId(Long localId) {
		this.localId = localId;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
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

}
