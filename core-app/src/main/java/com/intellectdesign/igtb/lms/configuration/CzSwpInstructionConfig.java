package com.intellectdesign.igtb.lms.configuration;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.serviceloader.ServiceListFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.intellectdesign.igtb.lms.cz.test.CzTestBusinessValidation;
import com.intellectdesign.igtb.lms.cz.test.CzTestRequestValidation;
import com.intellectdesign.igtb.lms.cz.test.CzTestService;
import com.intellectdesign.igtb.lms.exception.ApiSubError;

@Configuration
public class CzSwpInstructionConfig {

	private static final Logger LOGGER = LoggerFactory.getLogger(CzSwpInstructionConfig.class);

	@Bean
	public CzTestService<Object> exTestService() {
		LOGGER.info("Crating proxy for ExTestService implementations");

		final List<CzTestService> exTestRepositories = getExTestServices();
		LOGGER.info("Found ExTestService implementations : {} ", exTestRepositories.size());

		for (final CzTestService exTestService : exTestRepositories) {

			LOGGER.info("List Of object : {} ", exTestService.getClass().getName());
		}

		return exTestRepositories.stream().findFirst().get();
		// createProxiedNotificationSendersAsSpringWillNotCreateProxyForThese(senders);
	}

	@SuppressWarnings("unchecked")
	private List<CzTestService> getExTestServices() {
		List<CzTestService> senders = new ArrayList<>();
		try {
			final ServiceListFactoryBean serviceListFactoryBean = new ServiceListFactoryBean();
			serviceListFactoryBean.setServiceType(CzTestService.class);
			serviceListFactoryBean.afterPropertiesSet();

			senders = (List<CzTestService>) serviceListFactoryBean.getObject();
		} catch (Exception ex) {
			LOGGER.info("Unable to retrieve ExTestService implementations : {} ", ex.getMessage());
			ex.printStackTrace();
		}

		return senders;
	}

	@Bean
	public CzTestBusinessValidation<Object, ApiSubError> exTestBusinessValidation() {
		LOGGER.info("Crating proxy for ExTestBusinessValidation implementations");

		final List<CzTestBusinessValidation> list = getExTestBuisinessValidations();
		LOGGER.info("Found ExTestBusinessValidation implementations : {} ", list.size());

		CzTestBusinessValidation<Object, ApiSubError> exTestBusinessValidation = null;
		if (!list.isEmpty()) {
			exTestBusinessValidation = list.stream().findFirst().get();
		}

		return exTestBusinessValidation;
		// createProxiedNotificationSendersAsSpringWillNotCreateProxyForThese(senders);
	}

	@SuppressWarnings("unchecked")
	private List<CzTestBusinessValidation> getExTestBuisinessValidations() {
		List<CzTestBusinessValidation> senders = new ArrayList<>();
		try {
			final ServiceListFactoryBean serviceListFactoryBean = new ServiceListFactoryBean();
			serviceListFactoryBean.setServiceType(CzTestBusinessValidation.class);
			serviceListFactoryBean.afterPropertiesSet();

			senders = (List<CzTestBusinessValidation>) serviceListFactoryBean.getObject();
		} catch (Exception ex) {
			LOGGER.info("Unable to retrieve ExTestBuisinessValidation implementations : {} ", ex.getMessage());
			ex.printStackTrace();
		}

		return senders;
	}

	@Bean
	public CzTestRequestValidation exTestRequestValidation() {
		LOGGER.info("Crating proxy for ExTestRequestValidation implementations");

		final List<CzTestRequestValidation> exTestRepositories = getExTestRequestValidations();
		LOGGER.info("Found ExTestRequestValidation implementations : {} ", exTestRepositories.size());

		return exTestRepositories.stream().findFirst().get();
		// createProxiedNotificationSendersAsSpringWillNotCreateProxyForThese(senders);
	}

	@SuppressWarnings("unchecked")
	private List<CzTestRequestValidation> getExTestRequestValidations() {
		List<CzTestRequestValidation> senders = new ArrayList<>();
		try {
			final ServiceListFactoryBean serviceListFactoryBean = new ServiceListFactoryBean();
			serviceListFactoryBean.setServiceType(CzTestRequestValidation.class);
			serviceListFactoryBean.afterPropertiesSet();

			senders = (List<CzTestRequestValidation>) serviceListFactoryBean.getObject();
		} catch (Exception ex) {
			LOGGER.info("Unable to retrieve ExTestRequestValidation implementations : {} ", ex.getMessage());
			ex.printStackTrace();
		}

		return senders;
	}

}
