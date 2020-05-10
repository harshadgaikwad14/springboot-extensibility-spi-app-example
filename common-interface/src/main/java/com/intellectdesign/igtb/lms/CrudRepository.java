package com.intellectdesign.igtb.lms;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

import com.intellectdesign.igtb.lms.exception.ApiSubError;

public interface CrudRepository {

	default Object findAll(final Object object,final Map<String, String> requestInfoMap, final JdbcTemplate jdbcTemplate) throws Exception {
		return null;
	}

	default Object save(final Object object,final Map<String, String> requestInfoMap, final JdbcTemplate jdbcTemplate) throws Exception {
		return null;
	}

	default Object update(final Object object,final Map<String, String> requestInfoMap, final JdbcTemplate jdbcTemplate) throws Exception {
		return null;
	}

	default Object findById(final Object object,final Map<String, String> requestInfoMap, final JdbcTemplate jdbcTemplate) throws Exception {
		return null;
	}

	default List<ApiSubError> validate(final Object input,final Map<String, String> requestInfoMap, final JdbcTemplate jdbcTemplate) throws Exception {
		return null;
	}

}
