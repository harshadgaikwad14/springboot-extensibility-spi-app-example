package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.model.ExTaxType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ExTaxTypeValidationSpiImpl implements ExTaxTypeValidationSpi {

	public ExTaxTypeValidationSpiImpl() {
		super();
	}

	@Override
	public List<String> validate(final Object input) throws Exception {
		System.out.println("***************************************************************");
		System.out.println("********* ExTaxTypeValidationSpiImpl 0.0.1-SNAPSHOT *********************");
		System.out.println("***************************************************************");

		ExTaxType exTaxType = null;
		final ObjectMapper objectMapper = new ObjectMapper();
		try {
			final String objectAsString = objectMapper.writeValueAsString(input);
			exTaxType = objectMapper.readValue(objectAsString, ExTaxType.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		final List<String> errorList = new ArrayList<>();
		if (exTaxType.getTxTypCode() == null || exTaxType.getTxTypCode().isEmpty()) {
			errorList.add("TxTypCode is mandatory for extended model");
		}

		return errorList;

	}

}
