package com.intellectdesign.igtb.lms.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.intellectdesign.igtb.lms.ExSweepStructureSpi;
import com.intellectdesign.igtb.lms.entity.SweepInstruction;
import com.intellectdesign.igtb.lms.entity.SweepStructure;
import com.intellectdesign.igtb.lms.exception.DataNotFoundException;
import com.intellectdesign.igtb.lms.exception.PersistanceException;
import com.intellectdesign.igtb.lms.rowmapper.SweepStructureRowMapper;

@Repository
public class SweepStructureRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(SweepStructureRepository.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	PlatformTransactionManager transactionManager;

	@Autowired
	private ExSweepStructureSpi exSweepStructureSpiService;

	@Autowired
	private SweepInstructionRepository sweepInstructionRepository;

	public List<SweepStructure> findAll(final Map<String, String> requestInfoMap) throws Exception {

		Object extendedObject = null;
		JsonNode extendedObjectNode = null;
		ObjectMapper objectMapper = new ObjectMapper();

		final String finalQuery = "SELECT NBR_STRCID,COD_SUBPROD,COD_PRODUCT,DAT_STRCEFF,NBR_PRIORITY,NBR_INSTRUCTIONS,NBR_STRGRPID,FLG_STRTYPE,DAT_STRCSETUP,FLG_LOCK FROM OLM_STRUCTURE_HEADER";
		final List<SweepStructure> listOfStructures = jdbcTemplate.query(finalQuery, new SweepStructureRowMapper());

		extendedObject = exSweepStructureSpiService.findAll(null, requestInfoMap, jdbcTemplate);

		LOGGER.info("extendedObject : {}", extendedObject);

		if (extendedObject != null) {

			extendedObjectNode = objectMapper.convertValue(extendedObject, JsonNode.class);
			LOGGER.info("extendedObjectNode : {} ", extendedObjectNode);

		}

		final List<SweepStructure> swpStructures = new ArrayList<>();
		for (SweepStructure swpStructure : listOfStructures) {

			if (extendedObjectNode != null) {

				for (JsonNode node : extendedObjectNode) {
					final Long structureId = node.get("structureId").longValue();
					if (structureId.equals(swpStructure.getStructureId())) {
						final Object object = objectMapper.convertValue(node, Object.class);
						swpStructure.setExtObject(object);
						break;
					}
				}
			}

			swpStructures.add(swpStructure);

		}

		return swpStructures;
	}

	public SweepStructure findById(final Long structureId, final Map<String, String> requestInfoMap) throws Exception {

		LOGGER.info("=======> Started Find By Structure Id {} ", structureId);

		final String finalQuery = "SELECT NBR_STRCID,COD_SUBPROD,COD_PRODUCT,DAT_STRCEFF,NBR_PRIORITY,NBR_INSTRUCTIONS,NBR_STRGRPID,FLG_STRTYPE,DAT_STRCSETUP,FLG_LOCK FROM OLM_STRUCTURE_HEADER WHERE NBR_STRCID = :structureId";

		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		mapSqlParameterSource.addValue("structureId", structureId);

		SweepStructure sweeepStructure = null;
		try {
			sweeepStructure = namedParameterJdbcTemplate.queryForObject(finalQuery, mapSqlParameterSource,
					new SweepStructureRowMapper());

		} catch (EmptyResultDataAccessException e) {
			throw new DataNotFoundException("Structure Id " + structureId + " not found");
		}

		LOGGER.info("=======> Started findById Extended SweepStructure ");
		final Object extendedObject = exSweepStructureSpiService.findById(structureId, requestInfoMap, jdbcTemplate);

		LOGGER.info("findById :: extendedObject : {} ", extendedObject);

		if (extendedObject != null) {

			sweeepStructure.setExtObject(extendedObject);
		}

		LOGGER.info("=======> Ended findById Extended SweepStructure ");

		LOGGER.info("=======> Started Instructions by structure Id ");
		List<SweepInstruction> swpInstructions = sweepInstructionRepository.findByStructureId(structureId,
				requestInfoMap, jdbcTemplate);
		if (swpInstructions.size() > 0) {
			sweeepStructure.setSweepInstructions(swpInstructions);
		}

		LOGGER.info("=======> Ended Instructions by structure Id ");

		LOGGER.info("=======> Ended findById :: SweepStructure : {} ", sweeepStructure);
		return sweeepStructure;

	}

	public String save(final SweepStructure sweepStructure, final Map<String, String> requestInfoMap) throws Exception {

		LOGGER.info("sweepStructure : {} ", sweepStructure);

		TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
		TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);

		int val = 0;

		LOGGER.info("============== Parent Object Save Start =========================");

		try {

			val = jdbcTemplate.update(
					"INSERT INTO OLM_STRUCTURE_HEADER( NBR_STRCID,COD_SUBPROD,COD_PRODUCT,DAT_STRCEFF,NBR_PRIORITY,NBR_INSTRUCTIONS,NBR_STRGRPID,FLG_STRTYPE,DAT_STRCSETUP,FLG_LOCK ) VALUES ("
							+ sweepStructure.getStructureId() + ",'" + sweepStructure.getSubProductCode() + "','"
							+ sweepStructure.getProductCode() + "', TO_DATE('" + sweepStructure.getEffDate()
							+ " 00:00:00' , 'YYYY-MM-DD HH24:MI:SS') ," + sweepStructure.getNbrPriority() + ","
							+ sweepStructure.getNoOfInstructions() + "," + sweepStructure.getNbrGroupId() + ",'"
							+ sweepStructure.getStructureType() + "', TO_DATE('" + sweepStructure.getEffDate()
							+ " 00:00:00' , 'YYYY-MM-DD HH24:MI:SS'),'" + sweepStructure.getLockFlag() + "')");

			LOGGER.info("val : {} ", val);

			LOGGER.info("============== extended Object Save Start =========================");

			if (sweepStructure.getExtObject() != null) {

				int extendedObject = 0;

				extendedObject = (int) exSweepStructureSpiService.save(sweepStructure.getExtObject(), requestInfoMap,
						jdbcTemplate);

				LOGGER.info("extendedObject insert Reponse : {}", extendedObject);
			}

			LOGGER.info("============== extended Object Save End =========================");

			LOGGER.info("============== Instruction Save Start =========================");
			if (sweepStructure.getSweepInstructions() != null) {

				long rowCount = 1;
				for (final SweepInstruction sweepInstruction : sweepStructure.getSweepInstructions()) {
					sweepInstruction.setInstructionId(rowCount);
					sweepInstructionRepository.save(sweepInstruction, requestInfoMap, jdbcTemplate);
					rowCount++;
				}
			}
			LOGGER.info("============== Instruction Save End =========================");
			transactionManager.commit(transactionStatus);
			LOGGER.info("============== Parent Object Save end =========================");
		} catch (Exception ex) {
			val = 0;
			transactionManager.rollback(transactionStatus);

			throw new PersistanceException(ex.getMessage());

		}

		if (val == 1) {
			return "SUCCESS";
		}

		return "FAILED";
	}

	public String update(final SweepStructure sweepStructure, final Map<String, String> requestInfoMap)
			throws Exception {

		LOGGER.info("sweepStructure : {} ", sweepStructure);

		TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
		TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);

		int val = 0;

		LOGGER.info("============== Parent Object Update Start =========================");

		try {

			val = jdbcTemplate
					.update("UPDATE OLM_STRUCTURE_HEADER SET COD_SUBPROD = '" + sweepStructure.getSubProductCode()
							+ "', COD_PRODUCT = '" + sweepStructure.getProductCode() + "', DAT_STRCEFF = TO_DATE('"
							+ sweepStructure.getEffDate() + " 00:00:00' , 'YYYY-MM-DD HH24:MI:SS'),NBR_PRIORITY ="
							+ sweepStructure.getNbrPriority() + ",NBR_INSTRUCTIONS="
							+ sweepStructure.getNoOfInstructions() + ",NBR_STRGRPID=" + sweepStructure.getNbrGroupId()
							+ ",FLG_STRTYPE='" + sweepStructure.getStructureType() + "',DAT_STRCSETUP=TO_DATE('"
							+ sweepStructure.getSetupDate() + " 00:00:00' , 'YYYY-MM-DD HH24:MI:SS'),FLG_LOCK='"
							+ sweepStructure.getLockFlag() + "' WHERE NBR_STRCID=" + sweepStructure.getStructureId());

			LOGGER.info("val : {} ", val);

			LOGGER.info("============== extended Object Update Start =========================");

			if (sweepStructure.getExtObject() != null) {

				int extendedObject = 0;

				extendedObject = (int) exSweepStructureSpiService.update(sweepStructure.getExtObject(), requestInfoMap,
						jdbcTemplate);

				LOGGER.info("extendedObject update Reponse : {}", extendedObject);
			}

			LOGGER.info("============== extended Object update End =========================");

			LOGGER.info("============== Instruction update Start =========================");
			if (sweepStructure.getSweepInstructions() != null) {

				for (final SweepInstruction sweepInstruction : sweepStructure.getSweepInstructions()) {

					sweepInstructionRepository.update(sweepInstruction, requestInfoMap, jdbcTemplate);

				}
			}
			LOGGER.info("============== Instruction update End =========================");
			transactionManager.commit(transactionStatus);
			LOGGER.info("============== Parent Object update end =========================");
		} catch (Exception ex) {
			val = 0;
			transactionManager.rollback(transactionStatus);

			throw new PersistanceException(ex.getMessage());

		}

		if (val == 1) {
			return "SUCCESS";
		}

		return "FAILED";
	}

}
