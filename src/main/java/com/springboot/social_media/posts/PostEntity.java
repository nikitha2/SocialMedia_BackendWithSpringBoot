package com.springboot.social_media.posts;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springboot.social_media.user.UserEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "POSTS")
public class PostEntity {

	@Id
	@GeneratedValue
	private Long id;

	@Size(min = 2, message = "Description should be atleast 2 characters")
	private String description;

	@ManyToOne(fetch = FetchType.LAZY) // because we dont want to fetch user details when we fetch post details
	@JsonIgnore
	private UserEntity user;

	public PostEntity() {
	}

	public PostEntity(UserEntity user, String description) {
		super();
		this.user = user;
		this.description = description;
	}

	public PostEntity(Long id, String description, UserEntity user) {
		super();
		this.id = id;
		this.description = description;
		this.user = user;
	}

	@Override
	public String toString() {
		return "PostEntity [postId=" + id + ", description=" + description + ", user=" + user + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long postId) {
		this.id = postId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public UserEntity getUser() {
		return user;
	}

}
