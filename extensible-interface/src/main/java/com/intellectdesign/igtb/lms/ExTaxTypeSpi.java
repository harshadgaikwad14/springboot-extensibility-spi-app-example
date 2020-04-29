package com.intellectdesign.igtb.lms;

import java.sql.Connection;
import java.util.List;

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
	
	default List<String> validate(final Object input) throws Exception {
		System.out.println("Default Implementation Called");
		return null;
	}

}
