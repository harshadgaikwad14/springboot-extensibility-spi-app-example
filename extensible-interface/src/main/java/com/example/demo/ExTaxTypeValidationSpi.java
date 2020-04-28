package com.example.demo;

import java.util.List;

public interface ExTaxTypeValidationSpi {

	default List<String> validate(final Object input) throws Exception {
		System.out.println("Default Implementation Called");
		return null;
	}

	
	

}
