package com.intellectdesign.igtb.lms.entity;

import java.io.Serializable;
import java.time.LocalDate;

public class Tax implements Serializable {

	
	private static final long serialVersionUID = 1L;
	private String taxCode;
	private LocalDate taxEffDate;
	private String taxDesc;
	private String taxTypeCode;
	private Object extObject;

	public Tax() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getTaxCode() {
		return taxCode;
	}

	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}

	public LocalDate getTaxEffDate() {
		return taxEffDate;
	}

	public void setTaxEffDate(LocalDate taxEffDate) {
		this.taxEffDate = taxEffDate;
	}

	public String getTaxDesc() {
		return taxDesc;
	}

	public void setTaxDesc(String taxDesc) {
		this.taxDesc = taxDesc;
	}

	public String getTaxTypeCode() {
		return taxTypeCode;
	}

	public void setTaxTypeCode(String taxTypeCode) {
		this.taxTypeCode = taxTypeCode;
	}

	public Object getExtObject() {
		return extObject;
	}

	public void setExtObject(Object extObject) {
		this.extObject = extObject;
	}
	
	

}
