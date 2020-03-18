package com.example.demo;

import java.io.Serializable;

public class CoreModel implements Serializable {
	
	private long id;
	private String name;
	private Object extensibleObject;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Object getExtensibleObject() {
		return extensibleObject;
	}
	public void setExtensibleObject(Object extensibleObject) {
		this.extensibleObject = extensibleObject;
	}
	@Override
	public String toString() {
		return "CoreModel [id=" + id + ", name=" + name + ", extensibleObject=" + extensibleObject + "]";
	}
	
	
	
}
