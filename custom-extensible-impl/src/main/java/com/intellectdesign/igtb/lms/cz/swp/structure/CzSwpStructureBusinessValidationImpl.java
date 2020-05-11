package com.intellectdesign.igtb.lms.cz.swp.structure;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intellectdesign.igtb.lms.cz.entity.CzSweepStructure;
import com.intellectdesign.igtb.lms.cz.swp.structure.CzSwpStructureBusinessValidation;
import com.intellectdesign.igtb.lms.exception.ApiSubError;
import com.intellectdesign.igtb.lms.exception.ApiValidationError;

public class CzSwpStructureBusinessValidationImpl implements CzSwpStructureBusinessValidation<Object, ApiSubError> {

	public CzSwpStructureBusinessValidationImpl() {
		super();
	}

	@Override
	public List<ApiSubError> validate(final Object input,final Map<String, String> requestInfoMap, final JdbcTemplate jdbcTemplate) throws Exception {

		CzSweepStructure exSwpStructure = null;
		final ObjectMapper objectMapper = new ObjectMapper();
		List<ApiSubError> errorList = null;

		final String objectAsString = objectMapper.writeValueAsString(input);
		System.out.println("ExSwpStructureBusinessValidationImpl - validate : objectAsString :: " + objectAsString);
		exSwpStructure = objectMapper.readValue(objectAsString, CzSweepStructure.class);

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

}
