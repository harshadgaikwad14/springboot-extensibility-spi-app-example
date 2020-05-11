package com.intellectdesign.igtb.lms.cz.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.intellectdesign.igtb.lms.cz.entity.CzSweepStructure;

public class CzSwpStructureRowMapper implements RowMapper<CzSweepStructure> {

	public CzSweepStructure mapRow(ResultSet rs, int rowNum) throws SQLException {

		final CzSweepStructure exSweepStructure = new CzSweepStructure();
		exSweepStructure.setStructureId(rs.getLong("NBR_STRCID"));
		exSweepStructure.setExtField1(rs.getString("FIELD1"));
		exSweepStructure.setExtField2(rs.getString("FIELD2"));
		exSweepStructure.setExtField3(rs.getString("FIELD3"));

		return exSweepStructure;
	}

}