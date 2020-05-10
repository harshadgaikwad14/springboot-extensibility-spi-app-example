package com.intellectdesign.igtb.lms.cz.swp.instruction;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intellectdesign.igtb.lms.cz.entity.ExSweepInstruction;
import com.intellectdesign.igtb.lms.cz.swp.instruction.CzSwpInstructionBusinessValidation;
import com.intellectdesign.igtb.lms.exception.ApiSubError;
import com.intellectdesign.igtb.lms.exception.ApiValidationError;

public class CzSwpInstructionBusinessValidationImpl implements CzSwpInstructionBusinessValidation<Object, ApiSubError> {

	public CzSwpInstructionBusinessValidationImpl() {
		super();
	}

	@Override
	public List<ApiSubError> validate(final Object input, final Map<String, String> requestInfoMap,
			final JdbcTemplate jdbcTemplate) throws Exception {

		ExSweepInstruction exSweepInstruction = null;
		final ObjectMapper objectMapper = new ObjectMapper();
		List<ApiSubError> errorList = null;

		final String objectAsString = objectMapper.writeValueAsString(input);
		System.out.println("ExSwpInstructionBusinessValidationImpl - validate : objectAsString :: " + objectAsString);
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
