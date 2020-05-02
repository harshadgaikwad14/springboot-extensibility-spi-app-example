package com.intellectdesign.igtb.lms.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.intellectdesign.igtb.lms.model.ExSweepStructure;

public class ExSwpStructureRowMapper implements RowMapper<ExSweepStructure> {

	public ExSweepStructure mapRow(ResultSet rs, int rowNum) throws SQLException {

		final ExSweepStructure exSweepStructure = new ExSweepStructure();
		exSweepStructure.setStructureId(rs.getLong("NBR_STRCID"));
		exSweepStructure.setExtField1(rs.getString("FIELD1"));
		exSweepStructure.setExtField2(rs.getString("FIELD2"));
		exSweepStructure.setExtField3(rs.getString("FIELD3"));

		return exSweepStructure;
	}

}