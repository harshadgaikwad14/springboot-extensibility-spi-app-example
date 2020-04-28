package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.ExTaxTypeSpi;
import com.example.demo.ExTaxTypeValidationSpi;
import com.example.demo.model.TaxType;
import com.example.demo.repository.TaxTypeRepository;

@Service
public class TaxTypeService {

	@Autowired
	private TaxTypeRepository taxTypeRepository;

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
		
		/*
		 * final List<String> modelErrors = new ArrayList<>(); List<String>
		 * extendedModelErrors = null;
		 * 
		 * if (taxType.getExtObject() != null) {
		 * 
		 * 
		 * 
		 * if (exTaxTypeValidationSpiService instanceof ExTaxTypeValidationSpi) {
		 * 
		 * ExTaxTypeValidationSpi exTaxTypeValidationSpi = (ExTaxTypeValidationSpi)
		 * exTaxTypeValidationSpiService;
		 * 
		 * extendedModelErrors =
		 * exTaxTypeValidationSpi.validate(taxType.getExtObject()); }
		 * 
		 * System.out.println("extendedObject validation Reponse : " +
		 * extendedModelErrors); }
		 * 
		 * if (taxType.getTxTypCode() == null || taxType.getTxTypCode().isEmpty()) {
		 * modelErrors.add("TxTypCode is mandatory for extended model"); }
		 * 
		 * if(extendedModelErrors!=null) { modelErrors.addAll(extendedModelErrors); }
		 * 
		 * System.out.println("Alll Erroors : "+modelErrors);
		 */

		return taxTypeRepository.save(taxType);
	}

}
