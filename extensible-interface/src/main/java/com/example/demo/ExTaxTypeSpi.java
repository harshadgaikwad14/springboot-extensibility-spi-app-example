package com.example.demo;

import java.sql.Connection;

import org.springframework.jdbc.core.JdbcTemplate;

public interface ExTaxTypeSpi {

	default Object findAll(final Object object, final Object jdbcTemplate) throws Exception {
		return null;
	}

	default Object save(final Object object, final Connection connection) throws Exception {
		return null;
	}
	default Object save(final Object object, final JdbcTemplate jdbcTemplate) throws Exception {
		return null;
	}


	
	default Object update(final Object object, final Object jdbcTemplate) throws Exception {
		return null;
	}

	default Object findById(final Object object, final Object jdbcTemplate) throws Exception {
		return null;
	}

}
