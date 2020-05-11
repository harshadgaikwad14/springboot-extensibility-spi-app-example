package com.intellectdesign.igtb.lms.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import com.intellectdesign.igtb.lms.cz.swp.instruction.CzSwpInstructionRequestValidation;
import com.intellectdesign.igtb.lms.entity.SweepInstruction;
import com.intellectdesign.igtb.lms.exception.ApiSubError;
import com.intellectdesign.igtb.lms.exception.ApiValidationError;

@Component
@RequestScope
public class SweepInstructionValidation {

	private static final Logger LOGGER = LoggerFactory.getLogger(SweepInstructionValidation.class);

	@Autowired
	private CzSwpInstructionRequestValidation<Object, ApiSubError> czSwpInstructionRequestValidation;

	public List<ApiSubError> validate(final SweepInstruction sweepInstruction, final Map<String, String> requestInfoMap,
			final JdbcTemplate jdbcTemplate) throws Exception {

		LOGGER.info("sweepInstruction {} ", sweepInstruction);

		List<ApiSubError> extendedObjectErrorList = null;
		List<ApiSubError> errorList = new ArrayList<>();

		if (sweepInstruction.getExtObject() != null) {

			if (czSwpInstructionRequestValidation != null) {
				/*
				 * extendedObjectErrorList =
				 * exSweepInstructionSpiService.validate(sweepInstruction.getExtObject(),
				 * requestInfoMap, jdbcTemplate);
				 */

				LOGGER.info("start callling czSwpInstructionRequestValidation validate");
				extendedObjectErrorList = czSwpInstructionRequestValidation.validate(sweepInstruction.getExtObject(),
						requestInfoMap, jdbcTemplate);

				LOGGER.info("end callling czSwpInstructionRequestValidation validate");
			}

		}

		LOGGER.info("extendedObjectErrorList {} ", extendedObjectErrorList);

		if (sweepInstruction.getStructureId() == null) {
			final ApiSubError apiSubError1 = new ApiValidationError("SweepInstruction", "structureId",
					"Structure Id is mandatory");
			errorList.add(apiSubError1);

		}

		if (extendedObjectErrorList != null) {
			errorList.addAll(extendedObjectErrorList);
		}

		LOGGER.info("final validation errorList {} ", errorList);

		return errorList;

	}

}
