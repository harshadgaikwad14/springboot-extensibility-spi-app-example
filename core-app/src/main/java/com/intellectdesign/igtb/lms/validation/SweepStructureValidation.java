package com.intellectdesign.igtb.lms.validation;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.intellectdesign.igtb.lms.ExSweepStructureSpi;
import com.intellectdesign.igtb.lms.entity.SweepInstruction;
import com.intellectdesign.igtb.lms.entity.SweepStructure;
import com.intellectdesign.igtb.lms.exception.ApiSubError;
import com.intellectdesign.igtb.lms.exception.ApiValidationError;

@Component
public class SweepStructureValidation {

	private static final Logger LOGGER = LoggerFactory.getLogger(SweepStructureValidation.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private ExSweepStructureSpi exSweepStructureSpiService;

	@Autowired
	private SweepInstructionValidation sweepInstructionValidation;

	public List<ApiSubError> validate(final SweepStructure sweepStructure) throws Exception {

		LOGGER.info("sweepStructure {} ", sweepStructure);

		List<ApiSubError> errorList = new ArrayList<>();

		if (sweepStructure.getExtObject() != null) {

			List<ApiSubError> extendedObjectErrorList = exSweepStructureSpiService
					.validate(sweepStructure.getExtObject(), jdbcTemplate);
			LOGGER.info("extendedObjectErrorList {} ", extendedObjectErrorList);
			if (extendedObjectErrorList != null) {
				errorList.addAll(extendedObjectErrorList);
			}

		}

		if (sweepStructure.getStructureId() == null) {
			final ApiSubError apiSubError1 = new ApiValidationError("SweepStructure", "structureId",
					"Structure Id is mandatory");
			errorList.add(apiSubError1);

		}

		if (sweepStructure.getProductCode() == null || sweepStructure.getProductCode().isEmpty()) {
			final ApiSubError apiSubError1 = new ApiValidationError("SweepStructure", "productCode",
					"productCode is mandatory");
			errorList.add(apiSubError1);
		} else if (!"SWEEPS".equals(sweepStructure.getProductCode())) {
			final ApiSubError apiSubError1 = new ApiValidationError("SweepStructure", "productCode",
					"productCode should be SWEEPS");
			errorList.add(apiSubError1);
		}

		for (SweepInstruction swpInstr : sweepStructure.getSweepInstructions()) {

			List<ApiSubError> swpInstructionErrorList = sweepInstructionValidation.validate(swpInstr, jdbcTemplate);
			if (swpInstructionErrorList != null) {
				errorList.addAll(swpInstructionErrorList);
			}
		}

		LOGGER.info("final validation errorList {} ", errorList);

		return errorList;

	}

}
