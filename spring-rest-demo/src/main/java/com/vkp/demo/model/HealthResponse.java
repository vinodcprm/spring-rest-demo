package com.vkp.demo.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class HealthResponse {
	private String status;
	
	@JsonInclude(value = Include.NON_NULL)
	private Date currentTime;	
	
	public HealthResponse(String status) {
		super();
		this.status = status;
	}

	public HealthResponse(String status, Date currentTime) {
		super();
		this.status = status;
		this.currentTime = currentTime;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getCurrentTime() {
		return currentTime;
	}
	public void setCurrentTime(Date currentTime) {
		this.currentTime = currentTime;
	}	
	
}
