package com.intellectdesign.igtb.lms.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.intellectdesign.igtb.lms.entity.TaxType;
import com.intellectdesign.igtb.lms.service.TaxTypeService;

@RestController
@RequestMapping("/taxs")
public class TaxRestController {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(TaxRestController.class);

	@Autowired
	private TaxTypeService taxTypeService;

	@GetMapping
	public List<TaxType> getTaxTypes() throws Exception {

		return taxTypeService.findAll();
	}

	@GetMapping("/{txTypCode}")
	public TaxType getTaxTypes(@PathVariable(name = "txTypCode",required =  true) String txTypCode) throws Exception {

		return taxTypeService.findById(txTypCode);
	}
	
	@PostMapping
	public String save(@RequestBody TaxType taxType) throws Exception {
		
		LOGGER.info("taxType : {} ",taxType);

		return taxTypeService.save(taxType);
	}

}
