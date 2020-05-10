package com.intellectdesign.igtb.lms.cz.swp.instruction;

import java.util.List;
import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.intellectdesign.igtb.lms.cz.entity.ExSweepInstruction;
import com.intellectdesign.igtb.lms.cz.rowmapper.ExSwpInstructionRowMapper;

@Repository
public class CzSwpInstructionRepositoryImpl {

	public CzSwpInstructionRepositoryImpl() {
		super();

	}

	public List<ExSweepInstruction> findAll(final Map<String, String> requestInfoMap, final JdbcTemplate jdbcTemplate)
			throws Exception {

		System.out.println("ExSweepStructureSpiImpl - findAll");

		final String finalQuery = "SELECT NBR_STRCID,NBR_INSTRID,FIELD1,FIELD2,FIELD3 FROM EX_OLM_SOURCE_ACCOUNT_DTLS";
		final List<ExSweepInstruction> exSweepStructures = jdbcTemplate.query(finalQuery,
				new ExSwpInstructionRowMapper());

		return exSweepStructures;
	}

	public List<ExSweepInstruction> findByStructureId(final Long structureId, final Map<String, String> requestInfoMap,
			final JdbcTemplate jdbcTemplate) throws Exception {

		final String finalQuery = "SELECT NBR_STRCID,NBR_INSTRID,FIELD1,FIELD2,FIELD3 FROM EX_OLM_SOURCE_ACCOUNT_DTLS WHERE NBR_STRCID = "
				+ structureId;

		List<ExSweepInstruction> exSweepInstructions = null;
		try {
			exSweepInstructions = jdbcTemplate.query(finalQuery, new ExSwpInstructionRowMapper());

		} catch (EmptyResultDataAccessException e) {

			System.out.println("Sweep Structure Id " + structureId + " not found in extended model");
		}

		return exSweepInstructions;

	}

	public int save(final ExSweepInstruction exSweepInstruction, final Map<String, String> requestInfoMap,
			final JdbcTemplate jdbcTemplate) throws Exception {

		int val = 0;

		val = jdbcTemplate
				.update("INSERT INTO EX_OLM_SOURCE_ACCOUNT_DTLS (NBR_STRCID,NBR_INSTRID,FIELD1,FIELD2,FIELD3) VALUES ('"
						+ exSweepInstruction.getStructureId() + "','" + exSweepInstruction.getInstructionId() + "','"
						+ exSweepInstruction.getExtField1() + "','" + exSweepInstruction.getExtField2() + "','"
						+ exSweepInstruction.getExtField3() + "')");

		System.out.println(val + " records inserted");

		return val;

	}

	public int update(final ExSweepInstruction exSwpInstr, final Map<String, String> requestInfoMap,
			final JdbcTemplate jdbcTemplate) throws Exception {

		int val = jdbcTemplate.update("UPDATE EX_OLM_SOURCE_ACCOUNT_DTLS SET FIELD1 = '" + exSwpInstr.getExtField1()
				+ "', FIELD2 = '" + exSwpInstr.getExtField2() + "', FIELD3 = '" + exSwpInstr.getExtField3()
				+ "' WHERE NBR_STRCID = " + exSwpInstr.getStructureId() + " and NBR_INSTRID = "
				+ exSwpInstr.getInstructionId());

		System.out.println(val + " records updated");

		return val;

	}

}