package com.intellectdesign.igtb.lms.cz.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.intellectdesign.igtb.lms.cz.entity.CzSweepInstruction;

public class CzSwpInstructionRowMapper implements RowMapper<CzSweepInstruction> {

	public CzSweepInstruction mapRow(ResultSet rs, int rowNum) throws SQLException {

		final CzSweepInstruction exSweepStructure = new CzSweepInstruction();
		exSweepStructure.setStructureId(rs.getLong("NBR_STRCID"));
		exSweepStructure.setInstructionId(rs.getLong("NBR_INSTRID"));
		exSweepStructure.setExtField1(rs.getString("FIELD1"));
		exSweepStructure.setExtField2(rs.getString("FIELD2"));
		exSweepStructure.setExtField3(rs.getString("FIELD3"));

		return exSweepStructure;
	}

}