package com.intellectdesign.igtb.lms.cz.swp.instruction;

import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

import com.intellectdesign.igtb.lms.service.EntityService;

public interface CzSwpInstructionService<E> extends EntityService<E>{
	
	default Object findByStructureId(final Long structureId,final Map<String, String> requestInfoMap, final JdbcTemplate jdbcTemplate) throws Exception {
		return null;
	}


}
