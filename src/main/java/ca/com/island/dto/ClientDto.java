package ca.com.island.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class ClientDto {
	
	@Schema(description = "Name", 
            example = "Tom Maruik", required = true)
	private String name;
	
	@Schema(description = "E-mail", 
            example = "tom@treyi.com", required = true)
	private String email;
	

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
