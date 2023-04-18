package com.springboot.social_media.user;

import java.time.LocalDate;

public class UserBody {

	private String name;
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
