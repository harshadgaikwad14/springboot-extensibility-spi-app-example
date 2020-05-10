package com.intellectdesign.igtb.lms.cz.entity;

import java.io.Serializable;

public class ExTest implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String extField1;

	public ExTest() {
	}

	public String getExtField1() {
		return extField1;
	}

	public void setExtField1(String extField1) {
		this.extField1 = extField1;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "ExTest [id=" + id + ", extField1=" + extField1 + "]";
	}

}
