package com.intellectdesign.igtb.lms.model;

import java.io.Serializable;

public class ExTaxType implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String txTypCode;
	private String extField1;
	private String extField2;
	public String getTxTypCode() {
		return txTypCode;
	}
	public String getExtField1() {
		return extField1;
	}
	public void setExtField1(String extField1) {
		this.extField1 = extField1;
	}
	public String getExtField2() {
		return extField2;
	}
	public void setExtField2(String extField2) {
		this.extField2 = extField2;
	}
	public void setTxTypCode(String txTypCode) {
		this.txTypCode = txTypCode;
	}
	public ExTaxType(String txTypCode, String extField1, String extField2) {
		super();
		this.txTypCode = txTypCode;
		this.extField1 = extField1;
		this.extField2 = extField2;
	}
	public ExTaxType() {
		super();
		// TODO Auto-generated constructor stub
	}
	/*
	 * @Override public String toString() { return "ExTaxType [txTypCode=" +
	 * txTypCode + ", extField1=" + extField1 + ", extField2=" + extField2 + "]"; }
	 */
	
	
	

}
