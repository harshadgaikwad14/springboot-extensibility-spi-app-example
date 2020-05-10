package com.intellectdesign.igtb.lms;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intellectdesign.igtb.lms.cz.entity.ExSweepStructure;
import com.intellectdesign.igtb.lms.cz.rowmapper.ExSwpStructureRowMapper;
import com.intellectdesign.igtb.lms.exception.ApiSubError;
import com.intellectdesign.igtb.lms.exception.ApiValidationError;

public class ExSweepStructureSpiImpl implements ExSweepStructureSpi {

	public ExSweepStructureSpiImpl() {
		super();
		System.out.println("====> VERSION : 0.0.1-SNAPSHOT <====");
		//System.out.println("====> VERSION : 0.0.2-SNAPSHOT <====");
	}

	@Override
	public Object findAll(final Object object,final Map<String, String> requestInfoMap, final JdbcTemplate jdbcTemplate) throws Exception {

		System.out.println("ExSweepStructureSpiImpl - findAll");
		// final String finalQuery = "SELECT NBR_STRCID,FIELD1,FIELD2 FROM
		// EX_OLM_STRUCTURE_HEADER";
		final String finalQuery = "SELECT NBR_STRCID,FIELD1,FIELD2,FIELD3 FROM EX_OLM_STRUCTURE_HEADER";
		final List<ExSweepStructure> exSweepStructures = jdbcTemplate.query(finalQuery, new ExSwpStructureRowMapper());

		return exSweepStructures;
	}

	@Override
	public Object save(final Object object,final Map<String, String> requestInfoMap, final JdbcTemplate jdbcTemplate) throws Exception {

		ExSweepStructure exSwpStructure = null;
		final ObjectMapper objectMapper = new ObjectMapper();

		final String objectAsString = objectMapper.writeValueAsString(object);
		System.out.println("ExSweepStructureSpiImpl - save : objectAsString :: " + objectAsString);
		exSwpStructure = objectMapper.readValue(objectAsString, ExSweepStructure.class);

		int val = 0;

//		val = jdbcTemplate.update("INSERT INTO EX_OLM_STRUCTURE_HEADER (NBR_STRCID,FIELD1,FIELD2) VALUES ('"
//				+ exSwpStructure.getStructureId() + "','" + exSwpStructure.getExtField1() + "','"
//				+ exSwpStructure.getExtField2() + "')");

		val = jdbcTemplate.update("INSERT INTO EX_OLM_STRUCTURE_HEADER (NBR_STRCID,FIELD1,FIELD2,FIELD3) VALUES ('"
				+ exSwpStructure.getStructureId() + "','" + exSwpStructure.getExtField1() + "','"
				+ exSwpStructure.getExtField2() + "','" + exSwpStructure.getExtField3() + "')");

		System.out.println(val + " records inserted");

		return val;

	}

	@Override
	public Object findById(final Object object,final Map<String, String> requestInfoMap, final JdbcTemplate jdbcTemplate) throws Exception {

		final ObjectMapper objectMapper = new ObjectMapper();
		Long structureId = null;

		final String objectAsString = objectMapper.writeValueAsString(object);
		System.out.println("ExSweepStructureSpiImpl - findById : objectAsString :: " + objectAsString);
		structureId = objectMapper.readValue(objectAsString, Long.class);

		final String finalQuery = "SELECT NBR_STRCID,FIELD1,FIELD2,FIELD3 FROM EX_OLM_STRUCTURE_HEADER WHERE NBR_STRCID = "
				+ structureId;

		ExSweepStructure exSweepStructure = null;
		try {
			exSweepStructure = jdbcTemplate.queryForObject(finalQuery, new ExSwpStructureRowMapper());

		} catch (EmptyResultDataAccessException e) {
			// throw new DataNotFoundException("Sweep Structure Id " + structureId + " not
			// found in extended model");

			System.out.println("Sweep Structure Id " + structureId + " not found in extended model");
		}

		return exSweepStructure;

	}

	@Override
	public List<ApiSubError> validate(final Object input,final Map<String, String> requestInfoMap, final JdbcTemplate jdbcTemplate) throws Exception {

		ExSweepStructure exSwpStructure = null;
		final ObjectMapper objectMapper = new ObjectMapper();
		List<ApiSubError> errorList = null;

		final String objectAsString = objectMapper.writeValueAsString(input);
		System.out.println("ExSweepStructureSpiImpl - validate : objectAsString :: " + objectAsString);
		exSwpStructure = objectMapper.readValue(objectAsString, ExSweepStructure.class);

		if (exSwpStructure != null) {

			errorList = new ArrayList<>();

			if (exSwpStructure.getStructureId() == null) {
				final ApiSubError apiSubError1 = new ApiValidationError("ExSweepStructure", "structureId",
						"Structure Id is mandatory");
				errorList.add(apiSubError1);

			}

		}

		return errorList;
	}

	@Override
	public Object update(final Object object,final Map<String, String> requestInfoMap, final JdbcTemplate jdbcTemplate) throws Exception {

		ExSweepStructure exSwpStructure = null;
		final ObjectMapper objectMapper = new ObjectMapper();

		final String objectAsString = objectMapper.writeValueAsString(object);
		System.out.println("ExSweepStructureSpiImpl - update : objectAsString :: " + objectAsString);
		exSwpStructure = objectMapper.readValue(objectAsString, ExSweepStructure.class);

		int val = 0;

		val = jdbcTemplate.update("UPDATE EX_OLM_STRUCTURE_HEADER SET FIELD1 = '" + exSwpStructure.getExtField1()
				+ "', FIELD2 = '" + exSwpStructure.getExtField2() + "', FIELD3 = '" + exSwpStructure.getExtField3()
				+ "' WHERE NBR_STRCID = " + exSwpStructure.getStructureId());

		System.out.println(val + " records updated");

		return val;

	}

}
