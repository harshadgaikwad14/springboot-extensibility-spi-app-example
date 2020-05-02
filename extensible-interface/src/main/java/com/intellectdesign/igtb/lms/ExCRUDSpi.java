package com.intellectdesign.igtb.lms;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.intellectdesign.igtb.lms.exception.ApiSubError;

public interface ExCRUDSpi {

	default Object findAll(final Object object, final JdbcTemplate jdbcTemplate) throws Exception {
		return null;
	}

	default Object save(final Object object, final JdbcTemplate jdbcTemplate) throws Exception {
		return null;
	}

	default Object update(final Object object, final JdbcTemplate jdbcTemplate) throws Exception {
		return null;
	}

	default Object findById(final Object object, final JdbcTemplate jdbcTemplate) throws Exception {
		return null;
	}

	default List<ApiSubError> validate(final Object input, final JdbcTemplate jdbcTemplate) throws Exception {
		return null;
	}

}
