package com.example.demo.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.TaxType;
import com.example.demo.service.TaxTypeService;

@RestController
public class TaxTypeRestController {

	@Autowired
	private TaxTypeService taxTypeService;

	@GetMapping("/tax-types")
	public List<TaxType> getTaxTypes() throws Exception {

		return taxTypeService.findAll();
	}

	@GetMapping("/tax-types/{txTypCode}")
	public TaxType getTaxTypes(@PathVariable(name = "txTypCode",required =  true) String txTypCode) throws Exception {

		return taxTypeService.findById(txTypCode);
	}
	
	@PostMapping("/tax-types")
	public String save(@RequestBody TaxType taxType) throws Exception {

		return taxTypeService.save(taxType);
	}

}