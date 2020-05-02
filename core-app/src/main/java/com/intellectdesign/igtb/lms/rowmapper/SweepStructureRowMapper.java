package com.intellectdesign.igtb.lms.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.intellectdesign.igtb.lms.entity.SweepStructure;

public class SweepStructureRowMapper implements RowMapper<SweepStructure> {

	public SweepStructure mapRow(ResultSet rs, int rowNum) throws SQLException {

		final SweepStructure sweepStructure = new SweepStructure();
		sweepStructure.setStructureId(rs.getLong("NBR_STRCID"));
		sweepStructure.setEffDate(rs.getDate("DAT_STRCEFF").toLocalDate());
		sweepStructure.setSubProductCode(rs.getString("COD_SUBPROD"));
		sweepStructure.setProductCode(rs.getString("COD_PRODUCT"));
		sweepStructure.setNbrPriority(rs.getLong("NBR_PRIORITY"));
		sweepStructure.setNoOfInstructions(rs.getLong("NBR_INSTRUCTIONS"));
		sweepStructure.setNbrGroupId(rs.getLong("NBR_STRGRPID"));
		sweepStructure.setStructureType(rs.getString("FLG_STRTYPE"));
		sweepStructure.setSetupDate(rs.getDate("DAT_STRCSETUP").toLocalDate());
		sweepStructure.setLockFlag(rs.getString("FLG_LOCK"));

		return sweepStructure;
	}

}