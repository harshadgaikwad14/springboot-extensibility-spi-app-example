package com.intellectdesign.igtb.lms.cz.test;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

import com.intellectdesign.igtb.lms.cz.test.CzTestBusinessValidation;
import com.intellectdesign.igtb.lms.exception.ApiSubError;

public class CzTestBusinessValidationImpl implements CzTestBusinessValidation<Object, ApiSubError> {

	public CzTestBusinessValidationImpl() {
		super();
	}

	@Override
	public List<ApiSubError> validate(final Object entity, final Map<String, String> requestInfoMap,
			final JdbcTemplate jdbcTemplate) throws Exception {
		System.out.println("ExTestBuisinessValidationImpl - validate");
		return null;
	}

}
