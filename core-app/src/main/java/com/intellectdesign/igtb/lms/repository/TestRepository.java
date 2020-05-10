package com.intellectdesign.igtb.lms.repository;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.intellectdesign.igtb.lms.cz.test.CzTestService;
import com.intellectdesign.igtb.lms.entity.Test;

@Repository
public class TestRepository implements EntityRepository<Test> {

	@Autowired
	public CzTestService<Object> czTestService;

	private static final Logger LOGGER = LoggerFactory.getLogger(TestRepository.class);

	@Override
	public int save(final Test test, final Map<String, String> requestInfoMap) throws Exception {

		LOGGER.info("Test : {} ", test);
		LOGGER.info("czTestService : {} ", czTestService);
		int exTestResponse = -1;

		if (czTestService != null) {
			LOGGER.info("czTestService is calling");
			exTestResponse = czTestService.save(test.getExtObject(), requestInfoMap);
			LOGGER.info("czTestService is end");
		}

		LOGGER.info("exTestResponse : {} ", exTestResponse);
		return exTestResponse;

	}

	@Override
	public Test findById(final Test entity, final Map<String, String> requestInfoMap, final JdbcTemplate jdbcTemplate)
			throws Exception {

		LOGGER.info("czTestService : {} ", czTestService);
		Object exTestResponse = null;
		if (czTestService != null) {
			exTestResponse = czTestService.findById(null, requestInfoMap, jdbcTemplate);
		}
		final Test t = new Test();
		t.setId(101l);
		t.setName("Test");
		t.setExtObject(exTestResponse);

		return t;
	}

}
