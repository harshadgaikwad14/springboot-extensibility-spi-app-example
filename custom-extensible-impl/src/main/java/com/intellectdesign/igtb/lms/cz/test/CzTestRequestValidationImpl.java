package com.intellectdesign.igtb.lms.cz.test;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

import com.intellectdesign.igtb.lms.cz.test.CzTestRequestValidation;
import com.intellectdesign.igtb.lms.exception.ApiSubError;

public class CzTestRequestValidationImpl implements CzTestRequestValidation<Object, ApiSubError> {

	public CzTestRequestValidationImpl() {
		super();
	}

	@Override
	public List<ApiSubError> validate(final Object entity, final Map<String, String> requestInfoMap,
			final JdbcTemplate jdbcTemplate) throws Exception {
		System.out.println("ExTestRequestValidationImpl - validate");
		return null;
	}

}
