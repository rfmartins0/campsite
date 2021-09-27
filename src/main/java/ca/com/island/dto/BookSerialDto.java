package ca.com.island.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class BookSerialDto {

	@Schema(description = "Number of Reservation", 
            example = "977AGG-887HJ", required = true)
	private String serial;

	public BookSerialDto() {

	}

	public BookSerialDto(String serial) {
		this.setSerial(serial);
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

}
