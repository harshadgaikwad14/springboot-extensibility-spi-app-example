package com.intellectdesign.igtb.lms.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.intellectdesign.igtb.lms.configuration.RequestInfo;
import com.intellectdesign.igtb.lms.entity.SweepStructure;
import com.intellectdesign.igtb.lms.entity.Test;
import com.intellectdesign.igtb.lms.service.TestService;

@RestController
@RequestMapping("/tests")
public class TestController {

	private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

	@Autowired
	private TestService testService;

	@Autowired
	private RequestInfo requestInfo;

//	@GetMapping
//	public List<SweepStructure> getTaxTypes(final HttpServletRequest request) throws Exception {
//
//		return testService.findAll(requestInfo.getRequestInfo(request));
//	}
//
	@GetMapping
	public Test getTaxTypes(final HttpServletRequest request) throws Exception {

		return testService.findById(null, requestInfo.getRequestInfo(request));
	}

	@PostMapping
	public String save(@RequestBody Test test, final HttpServletRequest request) throws Exception {

		LOGGER.info("Test : {} ", test);

		return testService.save(test, requestInfo.getRequestInfo(request));
	}

//	@PutMapping("/{structureId}")
//	public String save(@RequestBody SweepStructure sweepStructure, @PathVariable("structureId") Long structureId,
//			final HttpServletRequest request) throws Exception {
//
//		LOGGER.info("sweepStructure : {} ", sweepStructure);
//
//		return testService.update(sweepStructure, requestInfo.getRequestInfo(request));
//	}

}
