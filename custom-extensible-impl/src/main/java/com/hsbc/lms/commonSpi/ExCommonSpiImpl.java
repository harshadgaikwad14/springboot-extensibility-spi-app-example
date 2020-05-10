package com.hsbc.lms.commonSpi;

import com.intellectdesign.igtb.lms.ExCommonSpi;

public class ExCommonSpiImpl implements ExCommonSpi {

	
	public ExCommonSpiImpl() {
		super();
	}

	@Override
	public String message() {
		System.out.println("ExCommonSpiImpl - message");
		return "Good Morning";
	}
}
