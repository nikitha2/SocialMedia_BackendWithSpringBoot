package com.springboot.social_media.modals;

import java.time.LocalDateTime;

public class ErrorResponseModal {

	private LocalDateTime timestamp;
	private String message;
	private String details;

	public ErrorResponseModal(LocalDateTime timestamp, String message, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}

}
