package com.example.demo.model;

import java.io.Serializable;

public class TaxType implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String txTypCode;
	private String txTypDesc;
	private String flgPostingCycle;
	private Object extObject;
	
	
	
	public TaxType() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TaxType(String txTypCode, String txTypDesc, String flgPostingCycle) {
		super();
		this.txTypCode = txTypCode;
		this.txTypDesc = txTypDesc;
		this.flgPostingCycle = flgPostingCycle;
	}
	public String getTxTypCode() {
		return txTypCode;
	}
	public void setTxTypCode(String txTypCode) {
		this.txTypCode = txTypCode;
	}
	public String getTxTypDesc() {
		return txTypDesc;
	}
	public void setTxTypDesc(String txTypDesc) {
		this.txTypDesc = txTypDesc;
	}
	public String getFlgPostingCycle() {
		return flgPostingCycle;
	}
	public void setFlgPostingCycle(String flgPostingCycle) {
		this.flgPostingCycle = flgPostingCycle;
	}
	public Object getExtObject() {
		return extObject;
	}
	public void setExtObject(Object extObject) {
		this.extObject = extObject;
	}
	@Override
	public String toString() {
		return "TaxType [txTypCode=" + txTypCode + ", txTypDesc=" + txTypDesc + ", flgPostingCycle=" + flgPostingCycle
				+ ", extObject=" + extObject + "]";
	}
	
	

}
