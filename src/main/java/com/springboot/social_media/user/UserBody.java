package com.springboot.social_media.user;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

public class UserBody {

	@Size(min=2, message="Name should be atleast 2 characters")
	@JsonProperty("user_name")
	private String name;
	
	@Past(message = "Birth date should be in the past")
	@JsonProperty("birth_date")
	private LocalDate birthDate;
	
	@Override
	public String toString() {
		return "UserBody [name=" + name + ", birthDate=" + birthDate + "]";
	}

	public UserBody(String name, LocalDate birthDate) {
		super();
		this.name = name;
		this.birthDate = birthDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

}
