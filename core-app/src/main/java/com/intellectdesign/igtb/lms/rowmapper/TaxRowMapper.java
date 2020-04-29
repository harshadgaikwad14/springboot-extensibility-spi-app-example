package com.intellectdesign.igtb.lms.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.intellectdesign.igtb.lms.entity.Tax;



public class TaxRowMapper implements RowMapper<Tax> {

	public Tax mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		final Tax tax = new Tax();
		

		return tax;
	}

}