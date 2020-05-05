package com.intellectdesign.igtb.lms;

import org.springframework.jdbc.core.JdbcTemplate;

public interface ExSweepInstructionSpi extends ExCRUDSpi {
	
	default Object findByStructureId(final Long structureId, final JdbcTemplate jdbcTemplate) throws Exception {
		return null;
	}


}
