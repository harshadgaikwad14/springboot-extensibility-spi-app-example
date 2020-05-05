package com.intellectdesign.igtb.lms.repository;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ValidationException;

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

	public List<SweepStructure> findAll() throws Exception {

		Object extendedObject = null;
		JsonNode extendedObjectNode = null;
		ObjectMapper objectMapper = new ObjectMapper();

		final String finalQuery = "SELECT NBR_STRCID,COD_SUBPROD,COD_PRODUCT,DAT_STRCEFF,NBR_PRIORITY,NBR_INSTRUCTIONS,NBR_STRGRPID,FLG_STRTYPE,DAT_STRCSETUP,FLG_LOCK FROM OLM_STRUCTURE_HEADER";
		final List<SweepStructure> listOfStructures = jdbcTemplate.query(finalQuery, new SweepStructureRowMapper());

		extendedObject = exSweepStructureSpiService.findAll(null, jdbcTemplate);

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

	public SweepStructure findById(final Long structureId) throws Exception {

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
		final Object extendedObject = exSweepStructureSpiService.findById(structureId, jdbcTemplate);

		LOGGER.info("findById :: extendedObject : {} ", extendedObject);

		if (extendedObject != null) {

			sweeepStructure.setExtObject(extendedObject);
		}

		LOGGER.info("=======> Ended findById Extended SweepStructure ");

		LOGGER.info("=======> Started Instructions by structure Id ");
		List<SweepInstruction> swpInstructions = sweepInstructionRepository.findByStructureId(structureId,
				jdbcTemplate);
		if (swpInstructions.size() > 0) {
			sweeepStructure.setSweepInstructions(swpInstructions);
		}

		LOGGER.info("=======> Ended Instructions by structure Id ");

		LOGGER.info("=======> Ended findById :: SweepStructure : {} ", sweeepStructure);
		return sweeepStructure;

	}

	public String save(final SweepStructure sweepStructure) throws Exception {

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

				extendedObject = (int) exSweepStructureSpiService.save(sweepStructure.getExtObject(), jdbcTemplate);

				LOGGER.info("extendedObject insert Reponse : {}", extendedObject);
			}

			LOGGER.info("============== extended Object Save End =========================");

			LOGGER.info("============== Instruction Save Start =========================");
			if (sweepStructure.getSweepInstructions() != null) {

				long rowCount =1;
				for (final SweepInstruction sweepInstruction : sweepStructure.getSweepInstructions()) {
					sweepInstruction.setInstructionId(rowCount);
					sweepInstructionRepository.save(sweepInstruction, jdbcTemplate);
					rowCount++;
				}
			}
			LOGGER.info("============== Instruction Save End =========================");
			transactionManager.commit(transactionStatus);
			LOGGER.info("============== Parent Object Save end =========================");
		} catch (Exception ex) {
			val = 0;
			transactionManager.rollback(transactionStatus);

			throw new ValidationException("Persistance Failed " + ex.getMessage());

		}

		if (val == 1) {
			return "SUCCESS";
		}

		return "FAILED";
	}

}
