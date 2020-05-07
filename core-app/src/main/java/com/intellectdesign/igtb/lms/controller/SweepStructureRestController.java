package com.intellectdesign.igtb.lms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.intellectdesign.igtb.lms.configuration.RequestInfo;
import com.intellectdesign.igtb.lms.entity.SweepStructure;
import com.intellectdesign.igtb.lms.service.SweepStructureService;

@RestController
@RequestMapping("/swp-structures")
public class SweepStructureRestController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SweepStructureRestController.class);

	@Autowired
	private SweepStructureService sweepStructureService;

	@Autowired
	private RequestInfo requestInfo;

	@GetMapping
	public List<SweepStructure> getTaxTypes(final HttpServletRequest request) throws Exception {

		return sweepStructureService.findAll(requestInfo.getRequestInfo(request));
	}

	@GetMapping("/{structureId}")
	public SweepStructure getTaxTypes(@PathVariable(name = "structureId", required = true) Long structureId,
			final HttpServletRequest request) throws Exception {

		return sweepStructureService.findById(structureId, requestInfo.getRequestInfo(request));
	}

	@PostMapping
	public String save(@RequestBody SweepStructure sweepStructure, final HttpServletRequest request) throws Exception {

		LOGGER.info("sweepStructure : {} ", sweepStructure);

		return sweepStructureService.save(sweepStructure, requestInfo.getRequestInfo(request));
	}

	@PutMapping("/{structureId}")
	public String save(@RequestBody SweepStructure sweepStructure, @PathVariable("structureId") Long structureId,
			final HttpServletRequest request) throws Exception {

		LOGGER.info("sweepStructure : {} ", sweepStructure);

		return sweepStructureService.update(sweepStructure, requestInfo.getRequestInfo(request));
	}

}
