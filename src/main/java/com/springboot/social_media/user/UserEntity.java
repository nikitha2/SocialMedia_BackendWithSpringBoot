package com.springboot.social_media.user;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

@Entity
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;

	@Size(min = 2, message = "Name should be atleast 2 characters")
	@JsonProperty("user_name")
	private String name;

	@Past(message = "Birth date should be in the past")
	@JsonProperty("birth_date")
	private LocalDate birthDate;

	public UserEntity() {
		super();
	}

	public UserEntity(@Size(min = 2, message = "Name should be atleast 2 characters") String name,
			@Past(message = "Birth date should be in the past") LocalDate birthDate) {
		super();
		this.name = name;
		this.birthDate = birthDate;
	}

	public UserEntity(Long id, @Size(min = 2, message = "Name should be atleast 2 characters") String name,
			@Past(message = "Birth date should be in the past") LocalDate birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", birthDate=" + birthDate + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
