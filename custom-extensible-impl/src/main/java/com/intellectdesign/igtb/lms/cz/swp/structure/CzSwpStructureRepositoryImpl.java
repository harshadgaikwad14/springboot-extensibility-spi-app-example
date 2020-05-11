package com.intellectdesign.igtb.lms.cz.swp.structure;

import java.util.List;
import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.intellectdesign.igtb.lms.cz.entity.CzSweepStructure;
import com.intellectdesign.igtb.lms.cz.rowmapper.CzSwpStructureRowMapper;

@Repository
public class CzSwpStructureRepositoryImpl {

	public CzSwpStructureRepositoryImpl() {
		super();
	}

	public List<CzSweepStructure> findAll(final Map<String, String> requestInfoMap, final JdbcTemplate jdbcTemplate)
			throws Exception {

		System.out.println("ExSwpStructureRepositoryImpl - findAll");

		final String finalQuery = "SELECT NBR_STRCID,FIELD1,FIELD2,FIELD3 FROM EX_OLM_STRUCTURE_HEADER";
		final List<CzSweepStructure> exSweepStructures = jdbcTemplate.query(finalQuery, new CzSwpStructureRowMapper());

		return exSweepStructures;
	}

	public int save(final CzSweepStructure exSwpStructure, final Map<String, String> requestInfoMap,
			final JdbcTemplate jdbcTemplate) throws Exception {

		final int val = jdbcTemplate
				.update("INSERT INTO EX_OLM_STRUCTURE_HEADER (NBR_STRCID,FIELD1,FIELD2,FIELD3) VALUES ('"
						+ exSwpStructure.getStructureId() + "','" + exSwpStructure.getExtField1() + "','"
						+ exSwpStructure.getExtField2() + "','" + exSwpStructure.getExtField3() + "')");

		System.out.println(val + " records inserted");

		return val;

	}

	public CzSweepStructure findById(final Long structureId, final Map<String, String> requestInfoMap,
			final JdbcTemplate jdbcTemplate) throws Exception {

		final String finalQuery = "SELECT NBR_STRCID,FIELD1,FIELD2,FIELD3 FROM EX_OLM_STRUCTURE_HEADER WHERE NBR_STRCID = "
				+ structureId;

		CzSweepStructure exSweepStructure = null;
		try {
			exSweepStructure = jdbcTemplate.queryForObject(finalQuery, new CzSwpStructureRowMapper());

		} catch (EmptyResultDataAccessException e) {
			// throw new DataNotFoundException("Sweep Structure Id " + structureId + " not
			// found in extended model");

			System.out.println("Sweep Structure Id " + structureId + " not found in extended model");
		}

		return exSweepStructure;

	}

	public int update(final CzSweepStructure exSwpStructure, final Map<String, String> requestInfoMap,
			final JdbcTemplate jdbcTemplate) throws Exception {

		final int val = jdbcTemplate.update("UPDATE EX_OLM_STRUCTURE_HEADER SET FIELD1 = '"
				+ exSwpStructure.getExtField1() + "', FIELD2 = '" + exSwpStructure.getExtField2() + "', FIELD3 = '"
				+ exSwpStructure.getExtField3() + "' WHERE NBR_STRCID = " + exSwpStructure.getStructureId());

		System.out.println(val + " records updated");

		return val;

	}

}
