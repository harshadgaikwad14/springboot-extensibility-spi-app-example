package com.intellectdesign.igtb.lms.validation;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.intellectdesign.igtb.lms.ExTaxTypeSpi;
import com.intellectdesign.igtb.lms.entity.TaxType;

@Component
public class TaxTypeValidation {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(TaxTypeValidation.class);

	@Autowired
	@Qualifier("exTaxTypeSpiService")
	private Object exTaxTypeSpiService;

	public List<String> validate(final TaxType taxType) throws Exception {
		
		LOGGER.info("taxType {} ",taxType);

		List<String> extendedObjectErrorList = null;
		List<String> errorList = new ArrayList<>();

		if (exTaxTypeSpiService instanceof List) {

			List<ExTaxTypeSpi> exTaxTypeSpis = (List<ExTaxTypeSpi>) exTaxTypeSpiService;
			for (final ExTaxTypeSpi exTaxTypeSpi : exTaxTypeSpis) {
				extendedObjectErrorList = exTaxTypeSpi.validate(taxType.getExtObject());
				break;

			}

		}
		
		LOGGER.info("extendedObjectErrorList {} ",extendedObjectErrorList);

		if (taxType.getTxTypCode() == null || taxType.getTxTypCode().isEmpty()) {
			errorList.add("TxTypCode is mandatory");
		}

		if (extendedObjectErrorList != null) {
			errorList.addAll(extendedObjectErrorList);
		}

		LOGGER.info("errorList {} ",errorList);
		
		return errorList;

	}

}
