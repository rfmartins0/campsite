package ca.com.island.dto;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;

public class ClientBookDto {

	@Schema(description = "Name", 
            example = "Tom Maruik", required = true)
	private String name;

	@Schema(description = "E-mail", 
            example = "tom@treyi.com", required = true)
	private String email;

	@Schema(description = "Start Date", 
            example = "2021-09-20", required = true)
	private LocalDate startDate;

	@Schema(description = "End Date", 
            example = "2021-09-22", required = true)
	private LocalDate endDate;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
