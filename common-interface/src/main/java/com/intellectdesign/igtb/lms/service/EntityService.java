package com.intellectdesign.igtb.lms.service;

import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

public interface EntityService<E> {

	default Object findAll(final E entity, final Map<String, String> requestInfoMap, final JdbcTemplate jdbcTemplate)
			throws Exception {
		return null;
	}

	default int save(final E entity, final Map<String, String> requestInfoMap) throws Exception {
		return -1;
	}

	default int save(final E entity, final JdbcTemplate jdbcTemplate) throws Exception {
		return -1;
	}

	default int save(final E entity, final Map<String, String> requestInfoMap, final JdbcTemplate jdbcTemplate)
			throws Exception {
		return -1;
	}

	default int update(final E entity, final Map<String, String> requestInfoMap) throws Exception {
		return -1;
	}

	default int update(final E entity, final JdbcTemplate jdbcTemplate) throws Exception {
		return -1;
	}

	default int update(final E entity, final Map<String, String> requestInfoMap, final JdbcTemplate jdbcTemplate)
			throws Exception {
		return -1;
	}

	default E findById(final E entity, final Map<String, String> requestInfoMap, final JdbcTemplate jdbcTemplate)
			throws Exception {
		return null;
	}

	

}