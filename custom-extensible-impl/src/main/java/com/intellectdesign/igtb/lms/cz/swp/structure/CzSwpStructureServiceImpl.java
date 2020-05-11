package com.intellectdesign.igtb.lms.cz.swp.structure;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intellectdesign.igtb.lms.cz.entity.CzSweepStructure;

public class CzSwpStructureServiceImpl implements CzSwpStructureService<Object> {

	@Autowired
	private CzSwpStructureRepositoryImpl exSwpStructureRepositoryImpl;

	public CzSwpStructureServiceImpl() {
		super();

	}

	@Override
	public Object findAll(final Object object, final Map<String, String> requestInfoMap,
			final JdbcTemplate jdbcTemplate) throws Exception {

		System.out.println("ExSweepStructureSpiImpl - findAll");

		return exSwpStructureRepositoryImpl.findAll(requestInfoMap, jdbcTemplate);
	}

	@Override
	public int save(final Object object, final Map<String, String> requestInfoMap, final JdbcTemplate jdbcTemplate)
			throws Exception {

		CzSweepStructure exSwpStructure = null;
		final ObjectMapper objectMapper = new ObjectMapper();

		final String objectAsString = objectMapper.writeValueAsString(object);
		System.out.println("ExSweepStructureSpiImpl - save : objectAsString :: " + objectAsString);
		exSwpStructure = objectMapper.readValue(objectAsString, CzSweepStructure.class);

		return exSwpStructureRepositoryImpl.save(exSwpStructure, requestInfoMap, jdbcTemplate);

	}

	@Override
	public Object findById(final Object object, final Map<String, String> requestInfoMap,
			final JdbcTemplate jdbcTemplate) throws Exception {

		final ObjectMapper objectMapper = new ObjectMapper();
		Long structureId = null;

		final String objectAsString = objectMapper.writeValueAsString(object);
		System.out.println("ExSweepStructureSpiImpl - findById : objectAsString :: " + objectAsString);
		structureId = objectMapper.readValue(objectAsString, Long.class);

		return exSwpStructureRepositoryImpl.findById(structureId, requestInfoMap, jdbcTemplate);

	}

	@Override
	public int update(final Object object, final Map<String, String> requestInfoMap, final JdbcTemplate jdbcTemplate)
			throws Exception {

		CzSweepStructure exSwpStructure = null;
		final ObjectMapper objectMapper = new ObjectMapper();

		final String objectAsString = objectMapper.writeValueAsString(object);
		System.out.println("ExSweepStructureSpiImpl - update : objectAsString :: " + objectAsString);
		exSwpStructure = objectMapper.readValue(objectAsString, CzSweepStructure.class);

		return exSwpStructureRepositoryImpl.update(exSwpStructure, requestInfoMap, jdbcTemplate);

	}

}
