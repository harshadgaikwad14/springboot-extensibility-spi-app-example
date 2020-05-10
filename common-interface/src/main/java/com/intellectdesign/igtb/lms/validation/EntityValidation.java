package com.intellectdesign.igtb.lms.validation;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

public interface EntityValidation<E, R> {

	default List<R> validate(final E entity, final Map<String, String> requestInfoMap, final JdbcTemplate jdbcTemplate)
			throws Exception {
		System.out.println("EntityValidation - validate");
		return null;
	}

}
