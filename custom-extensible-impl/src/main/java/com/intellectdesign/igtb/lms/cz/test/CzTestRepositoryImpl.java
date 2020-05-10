package com.intellectdesign.igtb.lms.cz.test;

import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intellectdesign.igtb.lms.cz.entity.ExTest;
import com.intellectdesign.igtb.lms.cz.test.CzTestRepository;

@Component
public class CzTestRepositoryImpl implements CzTestRepository<Object> {

	public CzTestRepositoryImpl() {
		super();
	}

	@Override
	public int save(final Object exTest, final Map<String, String> requestInfoMap) throws Exception {

		System.out.println("ExTestRepositoryImpl - save - ExTest : " + exTest);
		
		ExTest exTest2= null;
		final ObjectMapper objectMapper = new ObjectMapper();

		final String objectAsString = objectMapper.writeValueAsString(exTest);
		System.out.println("ExSweepStructureSpiImpl - save : objectAsString :: " + objectAsString);
		exTest2 = objectMapper.readValue(objectAsString, ExTest.class);
		
		if (exTest2 != null)
			return 1;
		return -1;
	}

	@Override
	public ExTest findById(final Object exTest, final Map<String, String> requestInfoMap,
			final JdbcTemplate jdbcTemplate) throws Exception {
		System.out.println("ExTestRepositoryImpl - findById - ExTest : " + exTest);

		final ExTest ex = new ExTest();
		ex.setId(101l);
		ex.setExtField1("Hard Coded Result");
		return ex;
	}
}
