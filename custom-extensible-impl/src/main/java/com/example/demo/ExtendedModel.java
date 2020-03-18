package com.example.demo;

import java.io.Serializable;

public class ExtendedModel implements Serializable {
	
	private long code;
	private String description;
	public long getCode() {
		return code;
	}
	public void setCode(long code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "ExtendedModel [code=" + code + ", description=" + description + "]";
	}
	
	
	

}
