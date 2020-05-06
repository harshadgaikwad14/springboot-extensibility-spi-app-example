package com.intellectdesign.igtb.lms;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intellectdesign.igtb.lms.exception.ApiSubError;
import com.intellectdesign.igtb.lms.exception.ApiValidationError;
import com.intellectdesign.igtb.lms.model.ExSweepInstruction;
import com.intellectdesign.igtb.lms.model.ExSweepStructure;
import com.intellectdesign.igtb.lms.rowmapper.ExSwpInstructionRowMapper;

public class ExSweepInstructionSpiImpl implements ExSweepInstructionSpi {

	public ExSweepInstructionSpiImpl() {
		super();
	}

	@Override
	public Object findAll(final Object object, final JdbcTemplate jdbcTemplate) throws Exception {

		System.out.println("ExSweepStructureSpiImpl - findAll");
		// final String finalQuery = "SELECT NBR_STRCID,FIELD1,FIELD2 FROM
		// EX_OLM_SOURCE_ACCOUNT_DTLS";
		final String finalQuery = "SELECT NBR_STRCID,NBR_INSTRID,FIELD1,FIELD2,FIELD3 FROM EX_OLM_SOURCE_ACCOUNT_DTLS";
		final List<ExSweepInstruction> exSweepStructures = jdbcTemplate.query(finalQuery,
				new ExSwpInstructionRowMapper());

		return exSweepStructures;
	}

	@Override
	public Object findByStructureId(final Long structureId, final JdbcTemplate jdbcTemplate) throws Exception {

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

	@Override
	public Object save(final Object object, final JdbcTemplate jdbcTemplate) throws Exception {

		ExSweepInstruction exSwpInstructions = null;
		final ObjectMapper objectMapper = new ObjectMapper();

		final String objectAsString = objectMapper.writeValueAsString(object);
		System.out.println("ExSweepStructureSpiImpl - save : objectAsString :: " + objectAsString);
		exSwpInstructions = objectMapper.readValue(objectAsString, ExSweepInstruction.class);

		int val = 0;

		val = jdbcTemplate
				.update("INSERT INTO EX_OLM_SOURCE_ACCOUNT_DTLS (NBR_STRCID,NBR_INSTRID,FIELD1,FIELD2,FIELD3) VALUES ('"
						+ exSwpInstructions.getStructureId() + "','" + exSwpInstructions.getInstructionId() + "','"
						+ exSwpInstructions.getExtField1() + "','" + exSwpInstructions.getExtField2() + "','"
						+ exSwpInstructions.getExtField3() + "')");

		System.out.println(val + " records inserted");

		return val;

	}

	@Override
	public Object update(final Object object, final JdbcTemplate jdbcTemplate) throws Exception {

		ExSweepInstruction exSwpInstr = null;
		final ObjectMapper objectMapper = new ObjectMapper();

		final String objectAsString = objectMapper.writeValueAsString(object);
		System.out.println("ExSweepStructureSpiImpl - update : objectAsString :: " + objectAsString);
		exSwpInstr = objectMapper.readValue(objectAsString, ExSweepInstruction.class);

		int val = 0;

		val = jdbcTemplate.update("UPDATE EX_OLM_SOURCE_ACCOUNT_DTLS SET FIELD1 = '" + exSwpInstr.getExtField1()
				+ "', FIELD2 = '" + exSwpInstr.getExtField2() + "', FIELD3 = '" + exSwpInstr.getExtField3()
				+ "' WHERE NBR_STRCID = " + exSwpInstr.getStructureId()+" and NBR_INSTRID = "+exSwpInstr.getInstructionId());

		System.out.println(val + " records updated");

		return val;

	}
	
	@Override
	public List<ApiSubError> validate(final Object input, final JdbcTemplate jdbcTemplate) throws Exception {

		ExSweepInstruction exSweepInstruction = null;
		final ObjectMapper objectMapper = new ObjectMapper();
		List<ApiSubError> errorList = null;

		final String objectAsString = objectMapper.writeValueAsString(input);
		System.out.println("ExSweepInstructionSpiImpl - validate : objectAsString :: " + objectAsString);
		exSweepInstruction = objectMapper.readValue(objectAsString, ExSweepInstruction.class);

		if (exSweepInstruction != null) {

			errorList = new ArrayList<>();

			if (exSweepInstruction.getStructureId() == null) {
				final ApiSubError apiSubError1 = new ApiValidationError("ExSweepInstruction", "structureId",
						"Structure Id is mandatory");
				errorList.add(apiSubError1);

			}

		}

		return errorList;
	}

}
