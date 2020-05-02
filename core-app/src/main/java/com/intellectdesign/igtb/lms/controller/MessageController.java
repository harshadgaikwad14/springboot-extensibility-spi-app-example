package com.intellectdesign.igtb.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.intellectdesign.igtb.lms.ExCommonSpi;

@RestController
@RequestMapping("/messages")
public class MessageController {

	@Autowired
	private ExCommonSpi exCommonSpiService;

	@GetMapping
	public String getMessage() {

		return exCommonSpiService.message();
	}

}
