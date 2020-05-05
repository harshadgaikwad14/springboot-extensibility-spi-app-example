package com.intellectdesign.igtb.lms.repository;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.intellectdesign.igtb.lms.ExSweepInstructionSpi;
import com.intellectdesign.igtb.lms.entity.SweepInstruction;
import com.intellectdesign.igtb.lms.exception.DataNotFoundException;
import com.intellectdesign.igtb.lms.rowmapper.SweepInstructionRowMapper;

@Repository
public class SweepInstructionRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(SweepInstructionRepository.class);

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	private ExSweepInstructionSpi exSweepInstructionSpiService;

	public List<SweepInstruction> findAll(final JdbcTemplate jdbcTemplate) throws Exception {

		Object extendedObject = null;
		JsonNode extendedObjectNode = null;
		ObjectMapper objectMapper = new ObjectMapper();

		final String finalQuery = "SELECT NBR_STRCID,NBR_INSTRID,COD_CIFSRCACCT,COD_CIFTGTACCT,FLG_CTRAODLIMIT,FLG_CTRLODLIMIT,FLG_CROSSCCY,FLG_END,FLG_FORCEDR,COD_FREQUENCY,NBR_PRIORITY,NBR_SRCACCT,FLG_REVERSE,FLG_SUSPEND,FLG_SWEEPTYP FROM OLM_SOURCE_ACCOUNT_DTLS;";
		final List<SweepInstruction> listOfInstructions = jdbcTemplate.query(finalQuery,
				new SweepInstructionRowMapper());

		extendedObject = exSweepInstructionSpiService.findAll(null, jdbcTemplate);

		LOGGER.info("extendedObject : {}", extendedObject);

		if (extendedObject != null) {

			extendedObjectNode = objectMapper.convertValue(extendedObject, JsonNode.class);
			LOGGER.info("extendedObjectNode : {} ", extendedObjectNode);

		}

		final List<SweepInstruction> swpInstructions = new ArrayList<>();
		for (SweepInstruction swpInstruction : listOfInstructions) {

			if (extendedObjectNode != null) {

				for (JsonNode node : extendedObjectNode) {
					final Long structureId = node.get("structureId").longValue();
					final Long instructionId = node.get("instructionId").longValue();
					if (structureId.equals(swpInstruction.getStructureId())
							&& instructionId.equals(swpInstruction.getInstructionId())) {
						final Object object = objectMapper.convertValue(node, Object.class);
						swpInstruction.setExtObject(object);
						break;
					}
				}
			}

			swpInstructions.add(swpInstruction);

		}

		return swpInstructions;
	}

	public List<SweepInstruction> findByStructureId(final Long structureId, final JdbcTemplate jdbcTemplate)
			throws Exception {

		LOGGER.info("=======> Started Instructions by structure Id : {} ", structureId);

		final ObjectMapper objectMapper = new ObjectMapper();

		final String finalQuery = "SELECT NBR_STRCID,NBR_INSTRID,COD_CIFSRCACCT,COD_CIFTGTACCT,FLG_CTRAODLIMIT,FLG_CTRLODLIMIT,FLG_CROSSCCY,FLG_END,FLG_FORCEDR,COD_FREQUENCY,NBR_PRIORITY,NBR_SRCACCT,FLG_REVERSE,FLG_SUSPEND,FLG_SWEEPTYP FROM OLM_SOURCE_ACCOUNT_DTLS WHERE NBR_STRCID = :structureId";

		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		mapSqlParameterSource.addValue("structureId", structureId);

		List<SweepInstruction> sweeepInstructions = null;
		JsonNode extendedObjectNode = null;
		try {
			sweeepInstructions = namedParameterJdbcTemplate.query(finalQuery, mapSqlParameterSource,
					new SweepInstructionRowMapper());

		} catch (EmptyResultDataAccessException e) {
			throw new DataNotFoundException("Structure Id " + structureId + " not found");
		}

		final Object extendedObject = exSweepInstructionSpiService.findByStructureId(structureId, jdbcTemplate);

		LOGGER.info("findById :: extendedObject : {} ", extendedObject);

		if (extendedObject != null) {

			extendedObjectNode = objectMapper.convertValue(extendedObject, JsonNode.class);
			LOGGER.info("extendedObjectNode : {} ", extendedObjectNode);

		}

		List<SweepInstruction> swpInstructions = new ArrayList<>();
		for (SweepInstruction sweeepInstruction : sweeepInstructions) {

			if (extendedObjectNode != null) {

				for (JsonNode node : extendedObjectNode) {
					final Long exStructureId = node.get("structureId").longValue();
					final Long exInstructionId = node.get("instructionId").longValue();
					if (exStructureId.equals(sweeepInstruction.getStructureId())
							&& exInstructionId.equals(sweeepInstruction.getInstructionId())) {
						final Object object = objectMapper.convertValue(node, Object.class);
						sweeepInstruction.setExtObject(object);
						break;
					}
				}
			}

			swpInstructions.add(sweeepInstruction);
		}

		LOGGER.info("findById :: SweeepInstructions : {} ", swpInstructions);
		return swpInstructions;

	}

	public SweepInstruction findById(final Long structureId, final Long instructionId) throws Exception {

		final String finalQuery = "SELECT NBR_STRCID,NBR_INSTRID,COD_CIFSRCACCT,COD_CIFTGTACCT,FLG_CTRAODLIMIT,FLG_CTRLODLIMIT,FLG_CROSSCCY,FLG_END,FLG_FORCEDR,COD_FREQUENCY,NBR_PRIORITY,NBR_SRCACCT,FLG_REVERSE,FLG_SUSPEND,FLG_SWEEPTYP FROM OLM_SOURCE_ACCOUNT_DTLS WHERE NBR_STRCID = :structureId and NBR_INSTRID = : instructionId";

		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		mapSqlParameterSource.addValue("structureId", structureId);
		mapSqlParameterSource.addValue("instructionId", instructionId);

		SweepInstruction sweeepInstruction = null;
		try {
			sweeepInstruction = namedParameterJdbcTemplate.queryForObject(finalQuery, mapSqlParameterSource,
					new SweepInstructionRowMapper());

		} catch (EmptyResultDataAccessException e) {
			throw new DataNotFoundException("Structure Id " + structureId + " not found");
		}

		LOGGER.info("findById :: SweeepInstruction : {} ", sweeepInstruction);
		return sweeepInstruction;

	}

	public String save(final SweepInstruction sweepInstruction, final JdbcTemplate jdbcTemplate) throws Exception {

		LOGGER.info("sweepInstruction : {} ", sweepInstruction);

		int val = 0;

		val = jdbcTemplate.update(
				"INSERT INTO OLM_SOURCE_ACCOUNT_DTLS ( NBR_STRCID,NBR_INSTRID,COD_CIFSRCACCT,COD_CIFTGTACCT,FLG_CTRAODLIMIT,FLG_CTRLODLIMIT,FLG_CROSSCCY,FLG_END,FLG_FORCEDR,COD_FREQUENCY,NBR_PRIORITY,NBR_SRCACCT,FLG_REVERSE,FLG_SUSPEND,FLG_SWEEPTYP ) VALUES ("
						+ sweepInstruction.getStructureId() + "," + sweepInstruction.getInstructionId() + ","
						+ sweepInstruction.getCifSrcAcctCode() + "," + sweepInstruction.getCifTgtAcctCode() + ",'"
						+ sweepInstruction.getContraODLimitFag() + "','" + sweepInstruction.getControlODLimitFag()
						+ "','" + sweepInstruction.getCrossCcyFlag() + "','" + sweepInstruction.getEndFlag() + "','"
						+ sweepInstruction.getForceDrFlag() + "'," + sweepInstruction.getFrequencyCode() + ","
						+ sweepInstruction.getNbrPriority() + "," + sweepInstruction.getNbrSrcAcct() + ",'"
						+ sweepInstruction.getReverseFlag() + "','" + sweepInstruction.getSuspendFlag() + "','"
						+ sweepInstruction.getSweepTypeFlag() + "')");

		LOGGER.info("val : {} ", val);

		LOGGER.info("============== extended Object Save Start =========================");

		if (sweepInstruction.getExtObject() != null) {

			int extendedObject = 0;

			final ObjectMapper objectMapper = new ObjectMapper();
			final JsonNode extendedObjectNode = objectMapper.convertValue(sweepInstruction.getExtObject(),
					JsonNode.class);
			LOGGER.info("============== extended Object Convert To Json Node : {} ",extendedObjectNode);
			((ObjectNode)extendedObjectNode).put("instructionId", sweepInstruction.getInstructionId());
			LOGGER.info("============== After extended Object Convert To Json Node : {} ",extendedObjectNode);

			extendedObject = (int) exSweepInstructionSpiService.save(objectMapper.convertValue(extendedObjectNode,Object.class), jdbcTemplate);

			LOGGER.info("extendedObject insert Reponse : {} " , extendedObject);
		}

		LOGGER.info("============== extended Object Save End =========================");

		if (val == 1) {
			return "SUCCESS";
		}

		return "FAILED";
	}

}
