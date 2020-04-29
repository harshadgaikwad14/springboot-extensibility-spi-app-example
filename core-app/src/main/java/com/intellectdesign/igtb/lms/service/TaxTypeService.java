package com.intellectdesign.igtb.lms.service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intellectdesign.igtb.lms.entity.TaxType;
import com.intellectdesign.igtb.lms.exception.ValidationException;
import com.intellectdesign.igtb.lms.repository.TaxTypeRepository;
import com.intellectdesign.igtb.lms.validation.TaxTypeValidation;

@Service
public class TaxTypeService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TaxTypeService.class);

	@Autowired
	private TaxTypeRepository taxTypeRepository;

	@Autowired
	private TaxTypeValidation taxTypeValidation;

	/*
	 * @Autowired
	 * 
	 * @Qualifier("exTaxTypeValidationSpiService") private Object
	 * exTaxTypeValidationSpiService;
	 */

	public List<TaxType> findAll() throws Exception {
		return taxTypeRepository.findAll();
	}

	public TaxType findById(final String txTypeCode) throws Exception {

		return taxTypeRepository.findById(txTypeCode);
	}

	public String save(final TaxType taxType) throws Exception {

		LOGGER.info("taxType : {} ", taxType);

		List<String> errorlist = taxTypeValidation.validate(taxType);
		if (errorlist == null || errorlist.isEmpty()) {
			return taxTypeRepository.save(taxType);
		}
		
		final String errors = errorlist.stream().collect(Collectors.joining("~~")).toString();
		LOGGER.info("errors : {} ", errors);
		
		throw new ValidationException(errors);

		

	}

}
