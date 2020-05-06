package com.intellectdesign.igtb.lms.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intellectdesign.igtb.lms.entity.SweepStructure;
import com.intellectdesign.igtb.lms.exception.ApiSubError;
import com.intellectdesign.igtb.lms.exception.ValidationException;
import com.intellectdesign.igtb.lms.repository.SweepStructureRepository;
import com.intellectdesign.igtb.lms.validation.SweepStructureValidation;

@Service
public class SweepStructureService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SweepStructureService.class);

	@Autowired
	private SweepStructureRepository sweepStructureRepository;

	@Autowired
	private SweepStructureValidation sweepStructureValidation;

	public List<SweepStructure> findAll() throws Exception {
		LOGGER.info("Sweep Strucure Service Called");
		return sweepStructureRepository.findAll();
	}

	public SweepStructure findById(Long structureId) throws Exception {
		LOGGER.info("Sweep Strucure Service findById Called");
		return sweepStructureRepository.findById(structureId);
	}

	public String save(final SweepStructure sweepStructure) throws Exception {

		LOGGER.info("SweepStructure : {} ", sweepStructure);

		final List<ApiSubError> errorList = sweepStructureValidation.validate(sweepStructure);
		if (errorList.isEmpty()) {

			return sweepStructureRepository.save(sweepStructure);
		}

		throw new ValidationException(errorList);

	}
	
	public String update(final SweepStructure sweepStructure) throws Exception {

		LOGGER.info("SweepStructure : {} ", sweepStructure);

		/*final List<ApiSubError> errorList = sweepStructureValidation.validate(sweepStructure);
		if (errorList.isEmpty()) {*/

			return sweepStructureRepository.update(sweepStructure);
		/*
		 * }
		 * 
		 * throw new ValidationException(errorList);
		 */

	}

}
