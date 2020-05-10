package com.intellectdesign.igtb.lms.cz.test;

import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.intellectdesign.igtb.lms.cz.entity.ExTest;

public class CzTestServiceImpl implements CzTestService<Object> {

	private static final Logger LOGGER = Logger.getLogger(CzTestServiceImpl.class.getName());

	@Autowired
	private CzTestRepository<Object> exTestRepositoryImpl;

	public CzTestServiceImpl() {
		super();
	}

	@Override
	public int save(final Object exTest, final Map<String, String> requestInfoMap) throws Exception {

		LOGGER.info("ExTestRepositoryImpl - save - ExTest :" + exTest);

		return exTestRepositoryImpl.save(exTest, requestInfoMap);
	}

	@Override
	public ExTest findById(final Object exTest, final Map<String, String> requestInfoMap,
			final JdbcTemplate jdbcTemplate) throws Exception {
		LOGGER.info("ExTestRepositoryImpl - findById - ExTest : " + exTest);

		return (ExTest) exTestRepositoryImpl.findById(exTest, requestInfoMap, jdbcTemplate);
	}
}
