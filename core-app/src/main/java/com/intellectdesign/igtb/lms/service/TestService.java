package com.intellectdesign.igtb.lms.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intellectdesign.igtb.lms.entity.Test;
import com.intellectdesign.igtb.lms.repository.TestRepository;

@Service
public class TestService {
	private static final Logger LOGGER = LoggerFactory.getLogger(TestService.class);

	@Autowired
	private TestRepository testRepository;

	public String save(final Test test, final Map<String, String> requestInfoMap) throws Exception {

		LOGGER.info("Test : {} ", test);

		int i = testRepository.save(test, requestInfoMap);

		if (i == 1)
			return "SUCCESS";

		return "FAILED";
	}

	public Test findById(final Test test, final Map<String, String> requestInfoMap) throws Exception {

		LOGGER.info("Test : {} ", test);

		return testRepository.findById(test, requestInfoMap, null);

	}

}
