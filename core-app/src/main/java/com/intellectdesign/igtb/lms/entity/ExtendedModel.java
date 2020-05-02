package com.intellectdesign.igtb.lms.entity;

import java.io.Serializable;

public abstract class ExtendedModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExtendedModel() {
		super();
	}

	@Override
	public String toString() {
		return "ExtendedModel [extObject=" + extObject + "]";
	}

	private Object extObject;

	public Object getExtObject() {
		return extObject;
	}

	public void setExtObject(Object extObject) {
		this.extObject = extObject;
	}

}
