package com.intellectdesign.igtb.lms.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import com.intellectdesign.igtb.lms.entity.SweepInstruction;

@Component
@RequestScope
public class SweepInstructionRowMapper implements RowMapper<SweepInstruction> {

	public SweepInstruction mapRow(ResultSet rs, int rowNum) throws SQLException {

		final SweepInstruction sweepInstruction = new SweepInstruction();
		sweepInstruction.setStructureId(rs.getLong("NBR_STRCID"));
		sweepInstruction.setInstructionId(rs.getLong("NBR_INSTRID"));
		sweepInstruction.setCifSrcAcctCode(rs.getString("COD_CIFSRCACCT"));
		sweepInstruction.setCifTgtAcctCode(rs.getString("COD_CIFTGTACCT"));
		sweepInstruction.setContraODLimitFag(rs.getString("FLG_CTRAODLIMIT"));
		sweepInstruction.setControlODLimitFag(rs.getString("FLG_CTRLODLIMIT"));
		sweepInstruction.setCrossCcyFlag(rs.getString("FLG_CROSSCCY"));
		sweepInstruction.setEndFlag(rs.getString("FLG_END"));
		sweepInstruction.setForceDrFlag(rs.getString("FLG_FORCEDR"));
		sweepInstruction.setFrequencyCode(rs.getString("COD_FREQUENCY"));
		sweepInstruction.setNbrPriority(rs.getLong("NBR_PRIORITY"));
		sweepInstruction.setNbrSrcAcct(rs.getString("NBR_SRCACCT"));
		sweepInstruction.setReverseFlag(rs.getString("FLG_REVERSE"));
		sweepInstruction.setSuspendFlag(rs.getString("FLG_SUSPEND"));
		sweepInstruction.setSweepTypeFlag(rs.getString("FLG_SWEEPTYP"));
		return sweepInstruction;
	}

}