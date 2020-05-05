package com.intellectdesign.igtb.lms.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.intellectdesign.igtb.lms.model.ExSweepInstruction;

public class ExSwpInstructionRowMapper implements RowMapper<ExSweepInstruction> {

	public ExSweepInstruction mapRow(ResultSet rs, int rowNum) throws SQLException {

		final ExSweepInstruction exSweepStructure = new ExSweepInstruction();
		exSweepStructure.setStructureId(rs.getLong("NBR_STRCID"));
		exSweepStructure.setInstructionId(rs.getLong("NBR_INSTRID"));
		exSweepStructure.setExtField1(rs.getString("FIELD1"));
		exSweepStructure.setExtField2(rs.getString("FIELD2"));
		exSweepStructure.setExtField3(rs.getString("FIELD3"));

		return exSweepStructure;
	}

}