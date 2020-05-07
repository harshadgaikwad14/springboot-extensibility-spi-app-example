package com.intellectdesign.igtb.lms;

import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

public interface ExSweepInstructionSpi extends EntityRepository {
	
	default Object findByStructureId(final Long structureId,final Map<String, String> requestInfoMap, final JdbcTemplate jdbcTemplate) throws Exception {
		return null;
	}


}
