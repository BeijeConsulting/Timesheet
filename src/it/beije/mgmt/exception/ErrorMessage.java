package it.beije.mgmt.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;


public class ErrorMessage {
	
	private String time;
	private String status;
	private String message;

	public String getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time.toString();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}