package com.springboot.social_media.user;

public class User {

	private Integer id;
	private UserBody userBody;

	public User(Integer id, UserBody userBody) {
		this.id = id;
		this.userBody = userBody;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + userBody.getName() + ", birthDate=" + userBody.getBirthDate() + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public UserBody getUserBody() {
		return userBody;
	}

	public void setUserBody(UserBody userBody) {
		this.userBody = userBody;
	}

}
