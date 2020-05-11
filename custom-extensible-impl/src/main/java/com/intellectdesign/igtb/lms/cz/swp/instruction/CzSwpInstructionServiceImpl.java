package com.intellectdesign.igtb.lms.cz.swp.instruction;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intellectdesign.igtb.lms.cz.entity.CzSweepInstruction;
import com.intellectdesign.igtb.lms.cz.swp.instruction.CzSwpInstructionService;

public class CzSwpInstructionServiceImpl implements CzSwpInstructionService<Object> {

	@Autowired
	private CzSwpInstructionRepositoryImpl exSwpInstructionRepositoryImpl;

	public CzSwpInstructionServiceImpl() {
		super();

	}

	@Override
	public Object findAll(final Object object, final Map<String, String> requestInfoMap,
			final JdbcTemplate jdbcTemplate) throws Exception {

		System.out.println("ExSweepStructureSpiImpl - findAll");

		return exSwpInstructionRepositoryImpl.findAll(requestInfoMap, jdbcTemplate);
	}

	@Override
	public Object findByStructureId(final Long structureId, final Map<String, String> requestInfoMap,
			final JdbcTemplate jdbcTemplate) throws Exception {

		return exSwpInstructionRepositoryImpl.findByStructureId(structureId, requestInfoMap, jdbcTemplate);

	}

	@Override
	public int save(final Object object, final Map<String, String> requestInfoMap, final JdbcTemplate jdbcTemplate)
			throws Exception {

		CzSweepInstruction exSwpInstruction = null;
		final ObjectMapper objectMapper = new ObjectMapper();

		final String objectAsString = objectMapper.writeValueAsString(object);
		System.out.println("ExSweepStructureSpiImpl - save : objectAsString :: " + objectAsString);
		exSwpInstruction = objectMapper.readValue(objectAsString, CzSweepInstruction.class);

		return exSwpInstructionRepositoryImpl.save(exSwpInstruction, requestInfoMap, jdbcTemplate);

	}

	@Override
	public int update(final Object object, final Map<String, String> requestInfoMap, final JdbcTemplate jdbcTemplate)
			throws Exception {

		CzSweepInstruction exSwpInstr = null;
		final ObjectMapper objectMapper = new ObjectMapper();

		final String objectAsString = objectMapper.writeValueAsString(object);
		System.out.println("ExSweepStructureSpiImpl - update : objectAsString :: " + objectAsString);
		exSwpInstr = objectMapper.readValue(objectAsString, CzSweepInstruction.class);

		return exSwpInstructionRepositoryImpl.update(exSwpInstr, requestInfoMap, jdbcTemplate);

	}

}
